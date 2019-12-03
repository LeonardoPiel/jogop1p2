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
public class Acao {
    private int codigo;
    private String inf;
  
    private Arena arena;
    

    public Acao(int codigo, String inf) {
        this.codigo = codigo;
        this.inf = inf;
    }

    public Acao(Arena arena) {
        this.arena = arena;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }
    
    public void tratar(int c, Robo j, Robo oponente){
    }
    
}
