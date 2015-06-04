/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;

import br.com.magazine.entidade.Produto;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Groupo X
 */
public class ProdutoDAO {
    //sem imagem
    private final String stmtCadastraProduto = "insert into produto (titulo, autor, editora, categoria, preco, genero) values (?,?,?,?,?,?)";
    //private final String stmtCadastraProduto = "insert into produto (titulo, autor, editora, categoria, preco, genero, idImg) values (?,?,?,?,?,?)";
    private final String stmtAtualizaProduto = "update produto set titulo = ?, autor = ?, editora = ?, categoria = ?, preco = ?, genero = ? where idProduto = ?";

    public void cadastrarProduto (Produto p) throws ClassNotFoundException{ 
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtCadastraProduto, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getAutor());
            stmt.setString(3, p.getEditora());
            stmt.setString(4, p.getCategoria());
            stmt.setDouble(5, p.getPreco());
            stmt.setString(6, p.getGenero().getNome());
            //sem imagem
            //stmt.setInt(6, p.getidImg());
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir um produto no banco de dados. Origem: " + e.getMessage());
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
        public void atualizarProduto(Produto p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtAtualizaProduto);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getAutor());
            stmt.setString(3, p.getEditora());
            stmt.setString(4, p.getCategoria());
            stmt.setDouble(5, p.getPreco());
            stmt.setString(6, p.getGenero().getNome());
            stmt.setInt(7, p.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
