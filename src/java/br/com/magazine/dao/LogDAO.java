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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Groupo X
 */
public class LogDAO {

    private final String stmtInserirLog = "insert into log (fkCliente, fkProduto, pagina, acao, data) values (?,?,?,?,?)";
    private final String stmtListarLogs = "select * from log where fkcliente = ? and data between ? and ? order by data desc";

    public void insereLog(Log log) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtInserirLog, PreparedStatement.RETURN_GENERATED_KEYS);
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

    public List<Log> listarLogs(int idCliente, String de, String ate) throws SQLException, ClassNotFoundException, ParseException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListarLogs);
            stmt.setInt(1, idCliente);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date parsedDate = (java.util.Date) dateFormat.parse(de+" 00:00:00");
            Timestamp inicio = new java.sql.Timestamp(parsedDate.getTime());
            
            parsedDate = (java.util.Date) dateFormat.parse(ate+" 23:59:59");
            Timestamp fim = new java.sql.Timestamp(parsedDate.getTime());
            stmt.setTimestamp(2, inicio);
            stmt.setTimestamp(3, fim);
            rs = stmt.executeQuery();
            List<Log> listaLogs = new ArrayList();
            while (rs.next()) {
                Log log = new Log();
                log.setIdProduto(rs.getInt("fkProduto"));
                log.setAcao(rs.getString("acao"));
                log.setPagina(rs.getString("pagina"));
                log.setIdCliente(idCliente);
                log.setData(rs.getTimestamp("data"));
                listaLogs.add(log);
            }
            return listaLogs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set.Erro: " + ex.getMessage());
            }
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
