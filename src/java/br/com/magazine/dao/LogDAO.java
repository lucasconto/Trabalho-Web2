/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;
import br.com.magazine.entidade.Log;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Groupo X
 */
public class LogDAO {

    private final String stmtInsereLog = "insert into log (fkCliente, fkProduto, pagina, acao, data) values (?,?,?,?,?)";
     
    public void insereLog (Log log) throws ClassNotFoundException{ 
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtInsereLog, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, log.getIdCliente());
            stmt.setInt(2, log.getIdProduto());
            stmt.setString(3, log.getPagina());
            stmt.setString(4, log.getAcao());
            stmt.setTimestamp(5, log.getData());
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir um log no banco de dados. Origem: " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar statement. Ex = " + ex.getMessage());
            }
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexao. Ex = " + ex.getMessage());
            }
        }
    }
}
