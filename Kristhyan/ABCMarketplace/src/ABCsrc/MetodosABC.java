/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABCsrc;

import conexao.ConexaoSQL;
import controle.CtrlProduto;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
       //return  ctrlProduto.buscar(nome, conexaoSQL);
       //função que  buscará em outros servidores os produtos
       List<Produto> produtosFound = new ArrayList<>();
        try {
            InterfaceABC stub1 = (InterfaceABC) Naming.lookup("rmi://ip:porta/buscaProdutos");
            /*InterfaceABC stub2 = (InterfaceABC) Naming.lookup("rmi://ip:porta/buscaProdutos");
            InterfaceABC stub3 = (InterfaceABC) Naming.lookup("rmi://ip:porta/buscaProdutos");
            InterfaceABC stub4 = (InterfaceABC) Naming.lookup("rmi://ip:porta/buscaProdutos");
            InterfaceABC stub5 = (InterfaceABC) Naming.lookup("rmi://ip:porta/buscaProdutos");
            InterfaceABC stub6 = (InterfaceABC) Naming.lookup("rmi://ip:porta/buscaProdutos");*/
            List<Produto> buscaProdutoStub1 = stub1.buscar(nome);
            if(buscaProdutoStub1!=null)
                buscaProdutoStub1.stream().forEach((p) -> {
                    produtosFound.add(p);
                });
            /*List<Produto> buscaProdutoStub2 = stub2.buscar(nome);
            if(buscaProdutoStub2!=null)
                buscaProdutoStub2.stream().forEach((p) -> {
                    produtosFound.add(p);
                });
            List<Produto> buscaProdutoStub3 = stub3.buscar(nome);
            if(buscaProdutoStub3!=null)
                buscaProdutoStub3.stream().forEach((p) -> {
                    produtosFound.add(p);
                });
            List<Produto> buscaProdutoStub4 = stub4.buscar(nome);
            if(buscaProdutoStub4!=null)
                buscaProdutoStub4.stream().forEach((p) -> {
                    produtosFound.add(p);
                });
            List<Produto> buscaProdutoStub5 = stub5.buscar(nome);
            if(buscaProdutoStub5!=null)
                buscaProdutoStub5.stream().forEach((p) -> {
                    produtosFound.add(p);
                });
            List<Produto> buscaProdutoStub6 = stub6.buscar(nome);
            if(buscaProdutoStub6!=null)
                buscaProdutoStub6.stream().forEach((p) -> {
                    produtosFound.add(p);
                });*/
            return produtosFound;
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(MetodosABC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
