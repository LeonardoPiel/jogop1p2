/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.BancoDados;
import controller.ControleItensEspeciais;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author leona
 */
public class Jogo {
    private static int maxX = 20;
    private static int maxY = 20;
    
    private Arena arena;
    private Robo j1;
    private Robo j2;
    private BancoDados bd;
    private ControleItensEspeciais cItens;
    private List<ItensEspeciais> itensEspeciais;

    public Jogo() {
        this.bd = new BancoDados();
        this.cItens = new ControleItensEspeciais(this.bd);
        /*
         * é preciso passar as dimensões da arena para o controlador pois 
         * a posição dos itens é gerada desses dados
        */
    }

    public void iniciar() {
        System.out.println("O jogo iniciou");
        this.bd.criarConexao();
        this.itensEspeciais = new ArrayList<>();
        
        this.itensEspeciais.addAll(cItens.buscarItens(maxX, maxY));
        this.arena = new Arena(0, 20, 20, this.itensEspeciais);

        //Cada robô nasce nos pontos extremos do mapa
        Robo j1 = new Robo(0, 0, 10, "j1");
        Robo j2 = new Robo(20, 20, 10, "j2");
        this.j1 = j1;
        this.j2 = j2;
    }

    public void acabar() {
        this.bd.fecharConexao();
        final Robo ganhador = this.j1.getQtdVida() > this.j2.getQtdVida() ? this.j1 : this.j2;
        System.out.println("O jogo foi finalizado. o jogador " + ganhador.getNome() + " ganhou");
    }

    public void mostraJogador(Robo j) {
        System.out.println("---------------------------------------");
        System.out.println("vida " + j.getNome() + " => " + j.getQtdVida());

        System.out.println("posição jogador " + j.getNome() + " => " + j.getPosX() + "," + j.getPosY());

        if (j.getArmaEquipada() != null) {
            System.out.println("Arma " + j.getNome() + " => " + j.getArmaEquipada().getNome());
        } else {
            System.out.println("jogador " + j.getNome() + " ainda não tem arma");
        }
        System.out.println("---------------------------------------");
    }

    public void mostraItensEspeciais() {
        System.out.println("------------- Itens especiais no jogo -------------");
        for (ItensEspeciais i : this.arena.getItensEspeciais()) {
            if (i instanceof Arma) {
                ((Arma) i).mostraArma();
            }
            if (i instanceof Bomba) {
                System.out.println("bomba!! neste local =>"
                        + "\nx: " + i.getX()
                        + "\ny: " + i.getY());
            }
            if (i instanceof Virus) {
                System.out.println("Virus neste local =>"
                        + "\nx: " + i.getX()
                        + "\ny: " + i.getY());
            }

        }
        System.out.println("---------------------------------------");
    }

    public int acoesJogador(Robo j, Robo oponente) {
        j.checaVirus();
        System.out.println("jogador " + j.getNome() + " faça uma escolha:");
        System.out.println("1 andar cima, 2 andar baixo, 3 andar esquerda, 4 andar direita ");
        Scanner entrada = new Scanner(System.in);
        int s = 0;
        try {
            if (j.getArmaEquipada() != null) {
                System.out.println("Atacar oponente? 8 - sim / 9 - não");
                while (s < 8 || s > 9) { // tratando entradas
                    s = Integer.parseInt(entrada.nextLine());
                    if (s == 8) {
                        j.getArmaEquipada().atacar(oponente);
                    }
                }
                System.out.println("Agora realize um movimento");
                while (s < 1 || s > 4) { // tratando entradas
                    s = Integer.parseInt(entrada.nextLine());
                }
            } else {
                while (s < 1 || s > 4) { // tratando entradas
                    s = Integer.parseInt(entrada.nextLine());
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Você informou um formato de entrada errado e portanto perdeu a rodada");
            //e.printStackTrace();
        }
        if (!j.movimentar(s, this.arena)) {
            return 0;
        }
        System.out.println("Você entrou com " + s);
        for (ItensEspeciais i : this.arena.getItensEspeciais()) {
            if (i instanceof Arma) {

                /**
                 * Verificando se o jogador encontrou uma arma e caso sim, se
                 * ele deseja pegá-la.
                 */
                if (((j.getPosX() == i.getX()) && (j.getPosY() == i.getY())) && j.getArmaEquipada() == null) { // não tinha nenhuma arma
                    System.out.println("Deseja pegar a arma? (6 - sim / 7 - não) => " + ((Arma) i).getNome());
                    s = 0;
                    try {
                        while (s < 6 || s > 7) {
                            s = Integer.parseInt(entrada.nextLine());
                            if (s == 6) {
                                j.setArmaEquipada(((Arma) i)); //equipa a arma no jogador
                                this.arena.getItensEspeciais().remove(((Arma) i)); // remove a arma do mapa
                            } else {
                                System.out.println("Não pegou a arma");
                            }
                        }
                        return s;
                    } catch (NumberFormatException e) {
                        System.out.println("Você informou um formato de entrada errado e portanto perdeu a rodada");
                        
                    }
                    // tem arma e deseja substituir
                } else if (((j.getPosX() == i.getX()) && (j.getPosY() == i.getY())) && j.getArmaEquipada() != null) {
                    try {
                        System.out.println("Deseja substituir sua arma (" + j.getArmaEquipada().getNome() + ")? (6 - sim / 7 - não) nova arma => " + ((Arma) i).getNome());
                        s = 0;
                        while (s < 6 || s > 7) {
                            s = Integer.parseInt(entrada.nextLine());
                            System.out.println("seis" + s);
                            if (s == 6) {
                                this.arena.getItensEspeciais().add(j.getArmaEquipada());// adiciona a antiga arma do jogador no mapa
                                j.setArmaEquipada(((Arma) i)); // equipa a arma no jogador
                                this.arena.getItensEspeciais().remove(((Arma) i)); // remove a arma nova do mapa
                            } else {
                                System.out.println("Não pegou a arma");
                            }
                        }
                        return s;
                    } catch (NumberFormatException e) {
                        System.out.println("Você informou um formato de entrada errado e portanto perdeu a rodada");
                    }
                }
            }
            // verifica se o jogador pisou em bomba e caso sim o pune
            if (i instanceof Bomba) {
                if ((j.getPosX() == i.getX()) && (j.getPosY() == i.getY())) {
                    System.out.println("Bomba explodiu!!");
                    arena.getItensEspeciais().remove((Bomba) i);
                    if (j.receberDano(((Bomba) i).estourar())) {
                        return s;
                    }
                }
            }
            // verifica se o jogador pisou em virus
            if (i instanceof Virus) {
                if ((j.getPosX() == i.getX()) && (j.getPosY() == i.getY())) {
                    System.out.println("Pegou vírus!!");
                    j.setDoente(true, ((Virus) i));
                }
            }
        }
        return 0;
    }
    
    /*laço de repetição principal do jogo*/
    public void duelar() {
        while (this.j1.getQtdVida() > 0 && this.j2.getQtdVida() > 0) {
            /*enquanto ninguém morrer o jogo continua*/
            int jogou = 0;
            // mostra dados do jogo
            this.mostraJogador(this.j1);
            this.mostraJogador(this.j2);
            this.mostraItensEspeciais();

            while (jogou != 2) { // controlador principal das ações dos jogadores
                this.acoesJogador(j1, j2);
                jogou++;

                this.acoesJogador(j2, j1);
                jogou++;
            }
        }
        return;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciar();
        jogo.duelar();
        jogo.acabar();
    }
}
