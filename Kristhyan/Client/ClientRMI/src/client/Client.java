/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level; 
import java.util.logging.Logger;

/**
 *
 * @author kristhyanmatos
 */
public class Client {
    public static void main(String[] args) {
        try {
            Registry miRegistry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            InterfaceABC stub = (InterfaceABC) miRegistry.lookup("marketplaceABC");
            stub.extrato();
            System.out.println("olá, está indo tudo muito bem!");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
