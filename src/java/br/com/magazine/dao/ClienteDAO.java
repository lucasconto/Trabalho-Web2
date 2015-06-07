/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;

import br.com.magazine.entidade.Cliente;
import br.com.magazine.entidade.Editora;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo X
 */
public class ClienteDAO {

    private final String stmtCadastraCliente = "insert into Cliente (nome, sexo, cpf, nascimento, telefone, email, senha, cep, endereco, endnumero, endcomplemento, bairro, cidade, estado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String stmtAtualizaCliente = "update Cliente set nome = ?, sexo = ?, cpf = ?, nascimento = ?, telefone = ?, email = ?, senha = ?, cep = ?, endereco = ?, endnumero = ?, endcomplemento = ?, bairro = ?, cidade = ?, estado = ? where idCliente = ?";
    private final String stmtBuscarClienteId = "select * from Cliente where idcliente = ?";
//    private final String stmtListaCliente = "select * from Cliente";
    private final String stmtRemoveCliente = "update Cliente set inativo = 1 where idCliente = ?";
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
            con.commit();
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

    
    
    public void removerCliente(Cliente cliente) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtRemoveCliente);
            stmt.setLong(1, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao. Ex = " + ex.getMessage());
            }
        }
    }
    
    public Cliente buscarClienteId(Cliente cliente) throws SQLException, ClassNotFoundException, ParseException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtBuscarClienteId);
            stmt.setInt(1, cliente.getId());
            rs = stmt.executeQuery();
            rs.next();
            Cliente clienteRetorno = new Cliente();
            clienteRetorno.setId(cliente.getId());
            clienteRetorno.setNome(rs.getString("nome"));
            clienteRetorno.setSexo(rs.getString("sexo"));
            clienteRetorno.setCpf(rs.getString("cpf"));
            Date nascimento = rs.getDate("nascimento");
            clienteRetorno.setNascimento(nascimento);
            clienteRetorno.setTelefone(rs.getString("telefone"));
            clienteRetorno.setEmail(rs.getString("email"));
            clienteRetorno.setSenha(rs.getString("senha"));
            clienteRetorno.setCep(rs.getString("cep"));
            clienteRetorno.setEndereco(rs.getString("endereco"));
            clienteRetorno.setEndNumero(rs.getString("endnumero"));
            clienteRetorno.setEndComplemento(rs.getString("endcomplemento"));
            clienteRetorno.setBairro(rs.getString("bairro"));
            clienteRetorno.setCidade(rs.getString("cidade"));
            clienteRetorno.setEstado(rs.getString("estado"));

            return clienteRetorno;
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

}
