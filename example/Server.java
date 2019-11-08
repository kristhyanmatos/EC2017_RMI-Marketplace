import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class Server{
  public static void main(String[] args) {
    try{
        InterfaceRMI stub = new Add();
        Naming.rebind("rmi://localhost:5000/soma", stub);
    }catch(Exception e){
        System.out.println(e);
    }
  }
}