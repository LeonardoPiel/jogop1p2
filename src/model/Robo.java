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
    private int posX;
    private int posY;
    private double qtdVida;

    public double getQtdVida() {
        return qtdVida;
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
    
    private Arma armaEquipada;
    
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
    
    
}
