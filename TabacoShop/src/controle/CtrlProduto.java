/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.DAOProduto;
import conexao.ConexaoSQL;
import java.util.List;
import model.Produto;
/**
 *
 * @author Allef Fonseca
 */
public class CtrlProduto {
    public int inserir(Produto p, ConexaoSQL conexao){
        return new DAOProduto().inserir(p, conexao);
    }
    public List<Produto> buscar(String nome, ConexaoSQL conexao){
        return new DAOProduto().buscar(nome, conexao);
    }
    public int atualizar(Double quantidade, int id, ConexaoSQL conexao){
        return new DAOProduto().atualizar(quantidade, id, conexao);
    }
}
