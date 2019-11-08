//Depedências
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//classe .java que extende dependências do rmi e implementa a interface InterfaceRMI
public class Add extends UnicastRemoteObject implements InterfaceRMI{
    //construtor recebendo os paramentos para a classe superiror
    //na hierarquia através do método super()
    Add() throws RemoteException {
        super();
    }
    //@override advindo da interface
    //por favor, não ignore essa linha
    //ela é responsável por garantir que você está sobrescrevendo
    //corretamente o método da SuperClasse
    //além de te informar onde tem dependências dela
    //ao fazer alguma alteração na SuperClasse
    @Override
    public int add(int x, int y){
        return x+y;
    }
}