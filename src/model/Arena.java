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

    private int comprimento;
    private int altura;
    private int largura;
    private List<ItensEspeciais> itensEspeciais;

    public Arena(int comprimento, int altura, int largura, List<ItensEspeciais> itensEspeciais) {
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

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void desenhar(Robo j1, Robo j2) {
        String[][] matriz = new String[20][20];
        for (int linha = 0; linha < this.altura; linha++) {
            System.out.println("|");
            for (int coluna = 0; coluna < this.largura; coluna++) {
                    matriz[j1.getPosX()][j1.getPosY()] = "X1";
                    //matriz[j2.getPosX()][j2.getPosY()] = "X2";
            }
        }
    }
}
