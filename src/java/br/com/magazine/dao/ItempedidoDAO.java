/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;

import br.com.magazine.entidade.Itempedido;
import br.com.magazine.entidade.Pedido;
import br.com.magazine.entidade.Produto;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo X
 */
public class ItempedidoDAO {

    private final String stmtInserirItempedido = "insert into Itempedido (idPedido,idProduto,quantidade,valor) values (?,?,?,?)";
    private final String stmtAtualizaItempedido = "update Pedido set status = ? where idPedido = ?";
//    private final String stmtListaCliente = "select * from Cliente";
//    private final String stmtRemoveCliente = "delete from Cliente where idCliente = ?";
//    private final String stmtRemoveItemPedidoCliente = "delete from itempedido where idpedido = (select idpedido from pedido where idcliente = ?)";
//    private final String stmtRemovePedidoCliente = "delete from pedido where idpedido = (select idpedido from pedido where idcliente= ? )";
//    private final String stmtProcuraNome = "select * from Cliente where nome like ";
//    private final String stmtProcuraSobreNome = "select * from Cliente where sobrenome like ";
//    private final String stmtProcuraCPF = "select * from Cliente where cpf like ";

    public void cadastrarItemPedido(Itempedido p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtInserirItempedido, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, p.getIdPedido());
            stmt.setInt(2, p.getIdProduto());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getValor());
            stmt.executeUpdate();

            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir um item de pedido no banco de dados. Origem: " + e.getMessage());
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

    public void atualizarItempedido(Itempedido p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizaItempedido);
            stmt.setInt(1, p.getIdPedido());

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
//
//    public List<Cliente> listaClientes() throws SQLException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(stmtListaCliente);
//            rs = stmt.executeQuery();
//            List<Cliente> listaClientes = new ArrayList();
//            while (rs.next()) {
//                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("cpf"));
//                cliente.setIdCliente(rs.getInt("idcliente"));
//                listaClientes.add(cliente);
//            }
//            return listaClientes;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                rs.close();
//            } catch (Exception ex) {
//                System.out.println("Erro ao fechar result set.Erro: " + ex.getMessage());
//            }
//            try {
//                stmt.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar statement. Ex = " + ex.getMessage());
//            }
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar a conexao. Ex = " + ex.getMessage());
//            }
//        }
//
//    }
//    
//    public List<Cliente> procuraNome(String nome) throws SQLException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(stmtProcuraNome+"'%"+nome+"%'");
//            rs = stmt.executeQuery();
//            List<Cliente> listaClientes = new ArrayList();
//          
//            while (rs.next()) {
//                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("cpf"));
//                cliente.setIdCliente(rs.getInt("idcliente"));
//                listaClientes.add(cliente);
//            }
//            return listaClientes;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                rs.close();
//            } catch (Exception ex) {
//                System.out.println("Erro ao fechar result set.Erro: " + ex.getMessage());
//            }
//            try {
//                stmt.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar statement. Ex = " + ex.getMessage());
//            }
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar a conexao. Ex = " + ex.getMessage());
//            }
//        }
//
//    }
//    
//    
//    public List<Cliente> procuraSobreNome(String sobrenome) throws SQLException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(stmtProcuraSobreNome+"'%"+sobrenome+"%'");
//            rs = stmt.executeQuery();
//            List<Cliente> listaClientes = new ArrayList();
//          
//            while (rs.next()) {
//                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("cpf"));
//                cliente.setIdCliente(rs.getInt("idcliente"));
//                listaClientes.add(cliente);
//            }
//            return listaClientes;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                rs.close();
//            } catch (Exception ex) {
//                System.out.println("Erro ao fechar result set.Erro: " + ex.getMessage());
//            }
//            try {
//                stmt.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar statement. Ex = " + ex.getMessage());
//            }
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar a conexao. Ex = " + ex.getMessage());
//            }
//        }
//
//    }
//    
//        public List<Cliente> procuraCPF (String cpf) throws SQLException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(stmtProcuraCPF+"'%"+cpf+"%'");
//            rs = stmt.executeQuery();
//            List<Cliente> listaClientes = new ArrayList();
//          
//            while (rs.next()) {
//                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("cpf"));
//                cliente.setIdCliente(rs.getInt("idcliente"));
//                listaClientes.add(cliente);
//            }
//            return listaClientes;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                rs.close();
//            } catch (Exception ex) {
//                System.out.println("Erro ao fechar result set.Erro: " + ex.getMessage());
//            }
//            try {
//                stmt.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar statement. Ex = " + ex.getMessage());
//            }
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                System.out.println("Erro ao fechar a conexao. Ex = " + ex.getMessage());
//            }
//        }
//
//    }
//    
//    
//        public void removerCliente(Cliente cliente) throws SQLException{
//        Connection con = null;
//        PreparedStatement stmt1 = null;
//        PreparedStatement stmt2 = null;
//        PreparedStatement stmt3 = null;
//        try{
//            con = ConnectionFactory.getConnection();
//            stmt1 = con.prepareStatement(stmtRemoveItemPedidoCliente);
//            stmt1.setLong(1,cliente.getIdCliente());
//            stmt1.executeUpdate();
//            stmt2 = con.prepareStatement(stmtRemovePedidoCliente);
//            stmt2.setLong(1,cliente.getIdCliente());
//            stmt2.executeUpdate();
//            stmt3 = con.prepareStatement(stmtRemoveCliente);
//            stmt3.setLong(1,cliente.getIdCliente());
//            stmt3.executeUpdate();
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        } finally{
//            try{
//                stmt1.close();
//                stmt2.close();
//                stmt3.close();
//            }catch (Exception ex){
//                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
//            }
//            try{
//                con.close();
//            }catch(Exception ex){
//                System.out.println("Erro ao fechar conexao. Ex = "+ex.getMessage());
//            }
//        }
//    
//    }
}
