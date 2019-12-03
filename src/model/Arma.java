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
public class Arma extends ItensEspeciais {

    private String nome;
    private double coeficienteDano;
    private int tipo;

    /*
     1 - a distância (30)
     2 - perto (40)
     */
    public Arma(String nome, double cf, int tipo, int x, int y) {
        super(x, y);
        this.nome = nome;
        this.tipo = tipo;
        this.coeficienteDano = cf;
        //this.setCoeficienteDano(); // o coeficiente de dano é dado a partir do tipo de arma
    }
    
    

    public String getNome() {
        return nome;
    }

    public double getCoeficienteDano() {
        return coeficienteDano;
    }

 

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double atacar(Robo oponente) {
        /**
         * Cálculo realizado pela distância euclidiana
         */
        double eq = Math.pow((this.x - oponente.getPosX()), 2) + Math.pow((this.y - oponente.getPosY()), 2);
        double dist = Math.sqrt(eq);
        Random rand = new Random();
        if (this.tipo == 1) {
            if (dist > 10) {
                return 0;
            }
            oponente.receberDano((this.coeficienteDano / dist) * rand.nextDouble());
            return (this.coeficienteDano / dist) * rand.nextDouble();
        }else{
            oponente.receberDano((this.coeficienteDano / dist) * rand.nextDouble());
            return (this.coeficienteDano / dist) * rand.nextDouble();
        }
    }

    public void mostraArma() {
        System.out.println("nome: " + this.nome + "\n "
                + "coef dano: " + this.coeficienteDano + "\n"        
                + "x: " + this.getX() + "\n "
                + "y: " + this.getY() + "\n "
                + "tipo: " + (this.tipo == 1? "distância":"perto"));
    }
}
