/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Loja;

/**
 *
 * @author kristhyanmatos
 */
public class DAOLoja {
    public int inserir(Loja l, ConexaoSQL conexao){
        String sql = "INSERT INTO Loja (Nome, Ip, Porta, Mi) VALUES(?,?,?,?)";
        PreparedStatement pstmt = conexao.criarPreparedStatement(sql);
        
        try {
            pstmt.setString(1, l.getNome());
            pstmt.setString(2, l.getIp());
            pstmt.setInt(3, l.getPorta());
            pstmt.setString(4, l.getMi());
            return pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOLoja.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally{
            if(pstmt!=null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    java.util.logging.Logger.getLogger(DAOLoja.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    public List<Loja> buscar(ConexaoSQL conexao){
        List<Loja> lojas = new ArrayList<>();
        ResultSet resultSet = null;
        Statement stmt = null;
        String query = "SELECT l.Id, "
                + "l.Nome, "
                + "l.Ip, "
                + "l.Porta, "
                + "l.Mi "
                + "FROM Loja l";
        stmt = conexao.criarStatement();
        
        try{
            resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                Loja l = new Loja();
                l.setNome(resultSet.getString("Nome"));
                l.setIp(resultSet.getString("Ip"));
                l.setPorta(resultSet.getInt("porta"));
                l.setMi(resultSet.getString("Mi"));
                lojas.add(l);
            }
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(DAOLoja.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                java.util.logging.Logger.getLogger(DAOLoja.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return lojas;
    }
}
