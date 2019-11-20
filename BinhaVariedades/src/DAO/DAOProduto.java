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
import model.Produto;

/**
 *
 * @author kristhyanmatos
 */
public class DAOProduto {
    public int inserir(Produto p, ConexaoSQL conexao){
        String sql = "INSERT INTO Produto(Nome, Preco, Data, Loja, Unidades) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = conexao.criarPreparedStatement(sql);
        
        try {
            pstmt.setString(1, p.getNome());
            pstmt.setDouble(2, p.getPrice());
            pstmt.setString(3, p.getData());
            pstmt.setString(4, p.getLoja());
            pstmt.setDouble(5, p.getUnidades());
            return pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally{
            if(pstmt!=null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    java.util.logging.Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    public List<Produto> buscar(String nome, ConexaoSQL conexao){
        List<Produto> produtos = new ArrayList<>();
        ResultSet resultSet = null;
        Statement stmt = null;
        String query = "SELECT p.Id, "
                + "p.Nome, "
                + "p.Preco, "
                + "p.Data, "
                + "p.Loja, "
                + "p.Unidades "
                + "FROM Produto p "
                + "WHERE p.Nome LIKE '"+nome+"%' "
                + "ORDER BY p.Preco";
        stmt = conexao.criarStatement();
        
        try{
            resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                Produto p = new Produto();
                p.setId(resultSet.getInt("Id"));
                p.setNome(resultSet.getString("Nome"));
                p.setPrice(resultSet.getDouble("Preco"));
                p.setData(resultSet.getString("Data"));
                p.setLoja(resultSet.getString("Loja"));
                p.setUnidades(resultSet.getDouble("Unidades"));
                produtos.add(p);
            }
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }finally{
            try{
                resultSet.close();
                stmt.close();
            }catch(SQLException e){
                java.util.logging.Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return produtos;
    }
    public int atualizar(Double quantidade, int id, ConexaoSQL conexao){
        PreparedStatement pstmt = null;
        String sql = "UPDATE Produto SET Unidades = ? WHERE Id = ?";
        
        try{
           pstmt = conexao.criarPreparedStatement(sql);
           pstmt.setDouble(1,quantidade);
           pstmt.setInt(2, id);
           
           return pstmt.executeUpdate();
           
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return 0;
        }finally{
            try{
                pstmt.close();
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
