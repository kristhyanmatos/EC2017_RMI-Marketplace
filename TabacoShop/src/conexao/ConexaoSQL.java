/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Allef Fonseca
 */
public class ConexaoSQL {
    private Connection conexao;
    
    public boolean conectar (){
        try{
            String url = buscadbUrl();
            System.out.println(url);
            this.conexao = DriverManager.getConnection("jdbc:sqlite:"+url);
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexaoSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ConexaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }   
    
    public  boolean desconectar(){
        try{
           if(this.conexao.isClosed() == false ){
               this.conexao.close();
           } 
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    public Statement criarStatement(){
        try{
            return this.conexao.createStatement();
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    public PreparedStatement criarPreparedStatement(String url){
        try{
            return this.conexao.prepareStatement(url);
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    public String buscadbUrl() throws FileNotFoundException, IOException{
        BufferedReader bufferRead = new BufferedReader(new FileReader("db\\dburl.txt"));
            String url = bufferRead.readLine();
            bufferRead.close();
            return url;
    }
}
