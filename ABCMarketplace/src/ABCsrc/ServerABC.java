/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABCsrc;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krist
 */
public class ServerABC {
    public static void main(String[] args){
        System.setProperty("java.security.policy", "security.txt");
        System.setSecurityManager(new SecurityManager());
        
        try {
            InterfaceABC stub = new MetodosABC();
            Registry miRegistry = LocateRegistry.createRegistry(1234);
            miRegistry.rebind("marketplaceABC",stub);
            System.out.println("Servidor ligado!");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerABC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
