/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABCsrc;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krist
 */
public class ServerABC {
    public static void main(String[] args) {
        InterfaceABC stub = new MetodosABC();
        try {
            Naming.bind("rmi://localhost:5001/serverABC", stub);
        } catch (AlreadyBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ServerABC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
