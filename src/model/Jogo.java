/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author leona
 */
public class Jogo {

    private Arena arena;
    private Robo j1;
    private Robo j2;

    public Jogo(Arena a, Robo j1, Robo j2) {
        this.arena = a;
        this.j1 = j1;
        this.j2 = j2;
    }

    public void iniciar() {
        System.out.println("O jogo iniciou");
    }

    public void acabar() {
        final Robo ganhador = this.j1.getQtdVida() > this.j2.getQtdVida() ? this.j1 : this.j2;
        System.out.println("O jogo foi finalizado o jogador " + ganhador + " ganhou");
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

        System.out.println("------ Armas do jogo ------");

        for (ItensEspeciais i : this.arena.getItensEspeciais()) {
            if (i instanceof Arma) {
                ((Arma) i).mostraArma();
            }
            if (i instanceof Bomba) {
                System.out.println("bomba!!"
                        + "\nx: " +i.getX()
                        + "\ny: " +i.getY());
            }
        }
        System.out.println("---------------------------------------");
    }

    public int acoesJogador(Robo j, Robo oponente) {

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
            System.out.println("Você informou um formato de entrada errado");
            e.printStackTrace();
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
                        System.out.println("Você informou um formato de entrada errado");
                        e.printStackTrace();
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
                        System.out.println("Você informou um formato de entrada errado");
                        e.printStackTrace();
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
    

    public void logger(int s) {
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<ItensEspeciais> itensEspeciais = new ArrayList<ItensEspeciais>();

        Arma a1 = new Arma("Pistola", 1, 1, 1);
        Arma a2 = new Arma("Soco Inglês", 2, 19, 19);
        Arma a3 = new Arma("Torreta", 1, rand.nextInt(20), rand.nextInt(20));
        Arma a4 = new Arma("Luva Especial", 2, rand.nextInt(20), rand.nextInt(20));

        Bomba b1 = new Bomba(rand.nextInt(20), rand.nextInt(20));

        itensEspeciais.add(a1);
        itensEspeciais.add(a2);
        itensEspeciais.add(a3);
        itensEspeciais.add(a4);
        itensEspeciais.add(b1);

        Arena arena = new Arena(0, 20, 20, itensEspeciais);
        /**
         * Cada robô nasce nos pontos extremos do mapa
         */
        Robo j1 = new Robo(0, 0, 200, "j1");
        Robo j2 = new Robo(20, 20, 200, "j2");

        Jogo jogo = new Jogo(arena, j1, j2);
        jogo.iniciar();
        //arena.desenhar(j1, j2);

        jogo.mostraItensEspeciais();

        while (j1.getQtdVida() > 0 && j2.getQtdVida() > 0) {
            /*enquanto ninguém morrer o jogo continua*/
            int jogou = 0;
            // mostra dados do jogo
            jogo.mostraJogador(j1);
            jogo.mostraJogador(j2);
            jogo.mostraItensEspeciais();

            while (jogou != 2) { // controlador principal das ações dos jogadores
                jogo.acoesJogador(j1, j2);
                jogou++;

                jogo.acoesJogador(j2, j1);
                jogou++;
            }
        }
        jogo.acabar();
    }
}
