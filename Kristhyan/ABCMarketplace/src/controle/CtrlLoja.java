/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import conexao.ConexaoSQL;
import java.util.List;
import model.Loja;

/**
 *
 * @author kristhyanmatos
 */
public class CtrlLoja {
    public int inserir(Loja l, ConexaoSQL conexao){
        return new DAO.DAOLoja().inserir(l, conexao);
    }
    public List<Loja> buscar(ConexaoSQL conexao){
        return new DAO.DAOLoja().buscar(conexao);
    }
}
