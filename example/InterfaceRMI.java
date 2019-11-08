//Dependências
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote{
    //Estruta de chama de método em inteface.java
    public int add(int x, int y) throws RemoteException;
}