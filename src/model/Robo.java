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

    public Robo(int posX, int posY, double qtdVida, String nome) {
        this.posX = posX;
        this.posY = posY;
        this.qtdVida = qtdVida;
        this.nome = nome;
        this.armaEquipada = null;
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
    public boolean receberDano(double dano){
        if(this.qtdVida > 0){
            this.qtdVida = this.qtdVida - dano;
            return true;
        }else{
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
    
    public boolean agir(int acao){
        int x_atual = this.getPosX();
        int y_atual = this.getPosY();
        
        switch(acao){
            case 1:
                this.setPosY(y_atual + 1);
                break;
            case 2:
                this.setPosY(y_atual - 1);
                break;
            case 3:
                this.setPosX(x_atual - 1);
                break;
            case 4:
                this.setPosX(x_atual + 1);
                break;
            default:
                return false;
        }
        if(this.armaEquipada != null){
            this.getArmaEquipada().setX(this.posX);
            this.getArmaEquipada().setY(this.posY);
        }
        return true;
    }
}
