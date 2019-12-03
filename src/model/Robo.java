/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author leona
 */
public class Robo {

    private String nome;
    private int posX;
    private int posY;
    private double qtdVida;
    private Arma armaEquipada;
    private boolean doente;
    private Virus virus;

    public Robo(int posX, int posY, double qtdVida, String nome) {
        this.posX = posX;
        this.posY = posY;
        this.qtdVida = qtdVida;
        this.nome = nome;
        this.armaEquipada = null;
    }

    public boolean isDoente() {
        return doente;
    }

    public void setDoente(boolean doente, Virus v) {
        this.doente = doente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQtdVida() {
        return qtdVida;
    }

    public boolean receberDano(double dano) {
        if (this.qtdVida > 0) {
            this.qtdVida = this.qtdVida - dano;
            return true;
        } else {
            return false;
        }
    }

    public void setQtdVida(int qtdVida) {
        this.qtdVida = qtdVida;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public void setArmaEquipada(Arma armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    // este método controla a movimentação do robo
    // verifica os limites da arena em que o robô está e pune
    // pelas regras do jogo
    public boolean movimentar(int acao, Arena arena) {

        int x_atual = this.getPosX();
        int y_atual = this.getPosY();

        switch (acao) {
            case 1: // pra cima
                if (y_atual + 1 > arena.getAltura()) {
                    this.receberDano(5);
                    return false;
                }
                this.setPosY(y_atual + 1);
                break;
            case 2: // pra baixo
                if (y_atual - 1 < 0) {
                    this.receberDano(5);
                    return false;
                }
                this.setPosY(y_atual - 1);
                break;
            case 3: // pra esquerda
                if (x_atual - 1 < 0) {
                    this.receberDano(5);
                    return false;
                }
                this.setPosX(x_atual - 1);
                break;
            case 4: // pra direita
                if (x_atual + 1 > arena.getLargura()) {
                    this.receberDano(5);
                    return false;
                }
                this.setPosX(x_atual + 1);
                break;
            default:
                return false;
        }
        // caso tenha uma arma equipada, então o robo deve levá-la junto com ele
        // esse trecho faz o controle da movimentação da arma junto com o robo.
        if (this.armaEquipada != null) {
            this.getArmaEquipada().setX(this.posX);
            this.getArmaEquipada().setY(this.posY);
        }
        return true;
    }
}
