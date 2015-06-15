package br.com.magazine.dao;

import br.com.magazine.entidade.ItemPedido;
import br.com.magazine.entidade.Pedido;
import br.com.magazine.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {

    private final String stmtInserirItempedido = "insert into Itempedido (idPedido,idProduto, quantidade, valorunitario) values (?,?,?,?)";
    private final String stmtAtualizaItempedido = "update Itempedido set idPedido = ? , idProduto = ?,  quantidade = ?, valor = ? where idItempedido = ?";
    private final String stmtListaItempedido = "select * from Itempedido where idPedido = ?";
    private final String stmtRemoveItempedido = "delete from Itempedido where idItempedido = ?";
//    private final String stmtRemoveItemPedidoCliente = "delete from itempedido where idpedido = (select idpedido from pedido where idcliente = ?)";
//    private final String stmtRemovePedidoCliente = "delete from pedido where idpedido = (select idpedido from pedido where idcliente= ? )";
//    private final String stmtProcuraNome = "select * from Cliente where nome like ";
//    private final String stmtProcuraSobreNome = "select * from Cliente where sobrenome like ";
//    private final String stmtProcuraCPF = "select * from Cliente where cpf like ";

    public void cadastrarItemPedido(ItemPedido p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtInserirItempedido, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, p.getIdPedido());
            stmt.setInt(2, p.getProduto().getIdProduto());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getValorUnitario());
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

    public void atualizarItemPedido(ItemPedido p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizaItempedido);
            stmt.setInt(1, p.getIdPedido());
            stmt.setInt(2, p.getProduto().getIdProduto());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getValorUnitario());
            stmt.setInt(5, p.getIdItemPedido());
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

    public List<ItemPedido> listarItemPedido(Pedido p) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaItempedido);
            stmt.setInt(1, p.getIdPedido());
            rs = stmt.executeQuery();
            List<ItemPedido> listaItens = new ArrayList();
            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setIdItemPedido(rs.getInt("idItempedido"));
                item.setIdPedido(rs.getInt("idPedido"));
                item.getProduto().setIdProduto(rs.getInt("idProduto"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValorUnitario(rs.getDouble("valor"));
                listaItens.add(item);
            }
            return listaItens;

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
    

    public void removerItemPedido(ItemPedido item) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtRemoveItempedido);
            stmt.setInt(1, item.getIdItemPedido());
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
}