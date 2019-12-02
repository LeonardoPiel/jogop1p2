/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author leona
 */
public class Bomba extends ItensEspeciais {
    private double dano = 1;
    
    public Bomba(int x, int y) {
        super(x, y);
    }
    
    public double estourar(){
        Random rand = new Random();
        rand.nextDouble();
        return (this.dano)*rand.nextDouble() * 10;
    }
    
}
