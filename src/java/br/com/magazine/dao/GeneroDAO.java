/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;

import br.com.magazine.entidade.Genero;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Groupo X
 */
public class GeneroDAO {

    //sem imagem

    private final String stmtCadastraGenero = "insert into genero (genero) values (?)";
    private final String stmtListaGenero = "select * from genero";

    public void cadastrarGenero(Genero genero) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtCadastraGenero, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, genero.getNome());
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir um genero no banco de dados. Origem: " + e.getMessage());
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

    public List<Genero> listaGeneros() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaGenero);
            rs = stmt.executeQuery();
            List<Genero> listaGeneros = new ArrayList();
            while (rs.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("idgenero"));
                genero.setNome(rs.getString("genero"));
                listaGeneros.add(genero);
            }
            return listaGeneros;

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
