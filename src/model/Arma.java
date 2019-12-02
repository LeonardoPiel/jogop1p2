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
    public Arma(String nome, int tipo, int x, int y) {
        super(x, y);
        this.nome = nome;
        this.tipo = tipo;
        this.setCoeficienteDano(); // o coeficiente de dano é dado a partir do tipo de arma
    }
    
    public String getNome() {
        return nome;
    }
    
     public double getCoeficienteDano() {
        return coeficienteDano;
    }

    public void setCoeficienteDano() {
        if(this.tipo == 1){
            this.coeficienteDano = 30;
        }else{
            this.coeficienteDano = 40;
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
   public double atacar(double d, Robo oponente){
        double dist = Math.sqrt((this.x - oponente.getPosX())^2 + (this.y - oponente.getPosY())^2);
        if(dist > 3){
            return 0;
        } 
        Random rand = new Random();
        rand.nextDouble();
        return (this.coeficienteDano/d)*rand.nextDouble();
    }
    
    public void mostraArma(){
        System.out.println("coef dano: "+this.coeficienteDano+ "\n"
                + "nome: "+this.nome+"\n "
                + "tipo: "+this.tipo);
    }
    public static void main(String[] args) {
        Arma a = new Arma("Pistola", 1, 1,1);
        a.mostraArma();
        Random rand = new Random();
        System.out.println(rand.nextDouble()* 10);
    }
}
