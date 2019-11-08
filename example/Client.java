import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client{
    public static void main(String[] args) {
        try{
            InterfaceRMI stub =(InterfaceRMI) Naming.lookup("rmi://localhost:5000/soma");
            System.out.println("Respota: "+stub.add(34,4));
        }catch(Exception e){
            System.out.println(e);
        }
    }
}