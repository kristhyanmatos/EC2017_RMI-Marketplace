/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import conexao.ConexaoSQL;
import java.util.List;
import model.Extrato;

/**
 *
 * @author Allef Fonseca
 */
public class CtrlExtrato {
    public int inserir(Extrato e, ConexaoSQL conexao){
        return new DAO.DAOExtrato().inserir(e, conexao);
    }
    public List<Extrato> buscar(ConexaoSQL conexao){
        return new DAO.DAOExtrato().buscar(conexao);
    }
}
