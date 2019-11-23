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
public class Arma {

    private String nome;
    private double coeficienteDano;
    private int tipo;
    /*
     1 - a distância (30)
     2 - perto (40)
     */
    public Arma(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.setCoeficienteDano();
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
    
    public void mostraArma(){
        System.out.println("coef dano: "+this.coeficienteDano+ "\n"
                + "nome: "+this.nome+"\n "
                + "tipo: "+this.tipo);
    }
    public static void main(String[] args) {
        Arma a = new Arma("Pistola", 1);
        a.mostraArma();
    }
}
