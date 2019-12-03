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
public class Virus extends ItensEspeciais {

    private double m;
    private int n;
    private int tipo;

    public Virus(int tipo, int x, int y) {
        super(x, y);
        this.tipo = tipo;
        this.setM_N();
    }
    

    public double getM() {
        return m;
    }

    public void setM_N() {
        switch(this.tipo){
            case 1:
                this.m = 5;
                this.n = 3;
                break;
            case 2:
                this.m = 5;
                this.n = 2;
                break;
            default:
                this.m = 1;
                this.n = 1;
        }
    }
    
    public int getN() {
        return n;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    

}
