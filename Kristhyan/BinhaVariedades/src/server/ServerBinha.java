/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import ABCsrc.InterfaceABC;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krist
 */
public class ServerBinha {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "security.txt");
        System.setSecurityManager(new SecurityManager());
        try{    
            InterfaceABC stub = new MetodosBinha();
            Registry miRegistry = LocateRegistry.createRegistry(1222);
            miRegistry.rebind("BinhaVariedades",stub);
            System.out.println("Servidor binha atendendo!");
        }catch(RemoteException ex){
            Logger.getLogger(ServerBinha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
