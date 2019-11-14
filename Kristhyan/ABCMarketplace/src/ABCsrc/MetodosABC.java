/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABCsrc;

import conexao.ConexaoSQL;
import controle.CtrlExtrato;
import controle.CtrlLoja;
import controle.CtrlProduto;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Extrato;
import model.Loja;
import model.Produto;

/**
 *
 * @author krist
 */
public class MetodosABC implements InterfaceABC {
    CtrlProduto ctrlProduto = new CtrlProduto();
    CtrlLoja ctrlLoja = new CtrlLoja();
    CtrlExtrato ctrlExtrato = new CtrlExtrato();
    ConexaoSQL conexaoSQL = new ConexaoSQL();
    Date data = new Date();
    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    String dataAtual = dataFormatada.format(data);
    public MetodosABC(){
        conexaoSQL.conectar();
    }
    @Override
    public List<Produto> buscar(String nome) throws RemoteException {
       //return  ctrlProduto.buscar(nome, conexaoSQL);
       //função que  buscará em outros servidores os produtos
       List<Produto> produtosFound = new ArrayList<>();
        try {
            for(Loja l: ctrlLoja.buscar(conexaoSQL)){
                InterfaceABC stub = (InterfaceABC) Naming.lookup("rmi://"+l.getUrl());
                List<Produto> buscaProdutoStub = stub.buscar(nome);
                if(buscaProdutoStub!=null)
                    buscaProdutoStub.stream().forEach((p) -> {
                        produtosFound.add(p);
                    });
            }
            return produtosFound;
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(MetodosABC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean comprar(Produto produto) throws RemoteException {
        Extrato extrato = new Extrato();
        double taxa = 0.12;
        boolean r = false;
        try {
        for(Loja l: ctrlLoja.buscar(conexaoSQL)){
             if(l.getNome().equals(produto.getLoja())){
                extrato.setNome(produto.getNome());
                extrato.setData(dataAtual);
                extrato.setPrice(produto.getPrice()*taxa);
                InterfaceABC stub = (InterfaceABC) Naming.lookup("rmi://"+l.getUrl());
                r = stub.comprar(produto);
                ctrlExtrato.inserir(extrato, conexaoSQL);
             }
         }
         } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(MetodosABC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
        return r; 
    }

    @Override
    public List<Extrato> extrato() throws RemoteException {
        return ctrlExtrato.buscar(conexaoSQL);
    }
    
}
