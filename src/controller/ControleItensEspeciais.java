/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Arena;
import model.Arma;
import model.Bomba;
import model.ItensEspeciais;
import model.Virus;

/**
 *
 * @author leona
 */
public class ControleItensEspeciais implements ControleDefault<ItensEspeciais> {

    private BancoDados bd;

    public ControleItensEspeciais(BancoDados bd) {
        this.bd = bd;
    }

    public List<ItensEspeciais> buscarItens(int x, int y) {
        // método retorna todos os itens especiais da base de dados
        String sql = "SELECT nome, coeficienteDano, tipo, tipoArma, tipoVirus FROM itens";
        List<ItensEspeciais> itens = new ArrayList<>();
        Random rand = new Random();
        try {
            ResultSet rs = bd.querySQL(sql);
            while (rs.next()) {
                switch (Integer.parseInt(rs.getString("tipo"))) {
                    case 1:
                        Bomba b = new Bomba(rand.nextInt(x), rand.nextInt(y));
                        itens.add((Bomba) b);
                        break;
                    case 2:
                        Virus v = new Virus(Integer.parseInt(rs.getString("tipoVirus")), rand.nextInt(x), rand.nextInt(y));
                        itens.add((Virus) v);
                        break;
                    case 3:
                        Arma a = new Arma(rs.getString("nome"), Double.parseDouble(rs.getString("coeficienteDano")), Integer.parseInt(rs.getString("tipoArma")), rand.nextInt(x), rand.nextInt(y));
                        itens.add((Arma) a);
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    @Override
    public boolean inserir(ItensEspeciais t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(ItensEspeciais t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(ItensEspeciais t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItensEspeciais buscar(ItensEspeciais t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
