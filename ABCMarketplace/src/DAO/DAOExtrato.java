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
import model.Extrato;
/**
 *
 * @author kristhyanmatos
 */
public class DAOExtrato {
    public int inserir(Extrato e, ConexaoSQL conexao){
        String sql = "INSERT INTO Extrato(Nome, Data, Preco) VALUES(?,?,?)";
        PreparedStatement pstmt = conexao.criarPreparedStatement(sql);
        
        try {
            pstmt.setString(1, e.getNome());
            pstmt.setString(2, e.getData());
            pstmt.setDouble(3, e.getPrice());
            return pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOExtrato.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally{
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOExtrato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public List<Extrato> buscar(ConexaoSQL conexao){
        List<Extrato> extratos = new ArrayList<>();
        ResultSet resultSet = null;
        Statement stmt = null;
        String query = "SELECT e.Id, "
                + "e.Nome, "
                + "e.Data, "
                + "e.Preco "
                + "FROM Extrato e ";
        stmt = conexao.criarStatement();
        
        try{
            resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                Extrato e = new Extrato();
                e.setId(resultSet.getInt("Id"));
                e.setNome(resultSet.getString("Nome"));
                e.setData(resultSet.getString("Data"));
                e.setPrice(resultSet.getDouble("Preco"));
                extratos.add(e);
            }
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(DAOExtrato.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                java.util.logging.Logger.getLogger(DAOExtrato.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return extratos;
    }
}
