/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABCsrc;

import conexao.ConexaoSQL;
import controle.CtrlProduto;
import java.rmi.RemoteException;
import java.util.List;
import model.Extrato;
import model.Produto;

/**
 *
 * @author krist
 */
public class MetodosABC implements InterfaceABC {
    CtrlProduto ctrlProduto = new CtrlProduto();
    ConexaoSQL conexaoSQL = new ConexaoSQL();
    public MetodosABC(){
        conexaoSQL.conectar();
    }
    @Override
    public List<Produto> buscar(String nome) throws RemoteException {
       return  ctrlProduto.buscar(nome, conexaoSQL);
       
    }

    @Override
    public boolean comprar(Produto produto) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Extrato> extrato() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
