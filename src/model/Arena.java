/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author leona
 */
public class Arena {

    private double comprimento;
    private double altura;
    private double largura;
    private List<ItensEspeciais> itensEspeciais;

    public Arena(double comprimento, double altura, double largura, List<ItensEspeciais> itensEspeciais) {
        this.comprimento = comprimento;
        this.altura = altura;
        this.largura = largura;
        this.itensEspeciais = itensEspeciais;
    }

    public List<ItensEspeciais> getItensEspeciais() {
        return itensEspeciais;
    }

    public void setItensEspeciais(List<ItensEspeciais> itensEspeciais) {
        this.itensEspeciais = itensEspeciais;
    }
    
    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }
    
    public void desenhar(){
        System.out.println("desenhou arena");
    }
}
