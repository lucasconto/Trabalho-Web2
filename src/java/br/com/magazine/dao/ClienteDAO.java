/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;

import br.com.magazine.entidade.Cliente;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo X
 */
public class ClienteDAO {

    private final String stmtCadastraCliente = "insert into Cliente (nome, sexo, cpf, nascimento, telefone, email, senha, cep, endereco, endnumero, endcomplemento, bairro, cidade, estado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String stmtAtualizaCliente = "update Cliente set nome = ?, sexo = ?, cpf = ?, nascimento = ?, telefone = ?, email = ?, senha = ?, cep = ?, endereco = ?, endnumero = ?, endcomplemento = ?, bairro = ?, cidade = ?, estado = ? where idCliente = ?";
//    private final String stmtListaCliente = "select * from Cliente";
//    private final String stmtRemoveCliente = "delete from Cliente where idCliente = ?";
//    private final String stmtRemoveItemPedidoCliente = "delete from itempedido where idpedido = (select idpedido from pedido where idcliente = ?)";
//    private final String stmtRemovePedidoCliente = "delete from pedido where idpedido = (select idpedido from pedido where idcliente= ? )";
//    private final String stmtProcuraNome = "select * from Cliente where nome like ";
//    private final String stmtProcuraSobreNome = "select * from Cliente where sobrenome like ";
//    private final String stmtProcuraCPF = "select * from Cliente where cpf like ";

    

    public void cadastrarCliente(Cliente cliente) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtCadastraCliente, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setString(3, cliente.getCpf());
            stmt.setDate(4, cliente.getNascimento());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSenha());
            stmt.setString(8, cliente.getCep());
            stmt.setString(9, cliente.getEndereco());
            stmt.setString(10, cliente.getEndNumero());
            stmt.setString(11, cliente.getEndComplemento());
            stmt.setString(12, cliente.getBairro());
            stmt.setString(13, cliente.getCidade());
            stmt.setString(14, cliente.getEstado());
            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir um cliente no banco de dados. Origem: " + e.getMessage());
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

    public void atualizarCliente(Cliente cliente) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtAtualizaCliente);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setString(3, cliente.getCpf());
            stmt.setDate(4, cliente.getNascimento());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSenha());
            stmt.setString(8, cliente.getCep());
            stmt.setString(9, cliente.getEndereco());
            stmt.setString(10, cliente.getEndNumero());
            stmt.setString(11, cliente.getEndComplemento());
            stmt.setString(12, cliente.getBairro());
            stmt.setString(13, cliente.getCidade());
            stmt.setString(14, cliente.getEstado());
            stmt.setInt(15, cliente.getId());
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
