/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author krist
 */
public interface InterfaceABC extends Remote{
    public List<Produto> buscar(String nome) throws RemoteException;
    public boolean comprar(Produto produto) throws RemoteException;
    public List<Extrato> extrato() throws RemoteException;
}