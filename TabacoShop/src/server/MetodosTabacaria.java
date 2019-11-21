/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import ABCsrc.InterfaceABC;
import conexao.ConexaoSQL;
import controle.CtrlExtrato;
import controle.CtrlProduto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Extrato;
import model.Produto;
/**
 *
 * @author Allef Fonseca
 */
public class MetodosTabacaria extends UnicastRemoteObject implements InterfaceABC{
    CtrlExtrato ctrlExtrato = new CtrlExtrato();
    CtrlProduto ctrlProduto = new CtrlProduto();
    ConexaoSQL conexaoSQL = new ConexaoSQL();
    Date data = new Date();
    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    String dataAtual = dataFormatada.format(data);
    public MetodosTabacaria() throws RemoteException{
        super();
        conexaoSQL.conectar();
    }
    @Override
    public List<Produto> buscar(String nome) throws RemoteException {
       return  ctrlProduto.buscar(nome, conexaoSQL);
       
    }
    @Override
    public boolean comprar(Produto produto, double quantidade) throws RemoteException {
        Extrato extrato = new Extrato();
        extrato.setNome(produto.getNome());
        extrato.setPrice(produto.getPrice());
        extrato.setData(produto.getData());
        int a = ctrlExtrato.inserir(extrato, conexaoSQL);
        int b = ctrlProduto.atualizar(quantidade, produto.getId(), conexaoSQL);
        if(b==1&a==1)
            return true;
        else
            return false;
    }
    @Override
    public List<Extrato> extrato() throws RemoteException {
        return ctrlExtrato.buscar(conexaoSQL);
    }
}
