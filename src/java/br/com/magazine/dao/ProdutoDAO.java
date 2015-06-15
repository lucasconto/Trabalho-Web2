/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.dao;

import br.com.magazine.entidade.Editora;
import br.com.magazine.entidade.Genero;
import br.com.magazine.entidade.Produto;
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
public class ProdutoDAO {

    //sem imagem

    private final String stmtCadastraProduto = "insert into produto (titulo, autor, fkeditora, preco, fkgenero) values (?,?,?,?,?)";
    //private final String stmtCadastraProduto = "insert into produto (titulo, autor, editora, categoria, preco, genero, idImg) values (?,?,?,?,?,?)";
    private final String stmtAtualizaProduto = "update produto set titulo = ?, autor = ?, fkeditora = ?, preco = ?, fkgenero = ? where idProduto = ?";
    private final String stmtRemoveProduto = "update produto set inativo = true where idProduto = ?";
    private final String stmtListaProduto = "select * from produto";
    private final String stmtListaProdutosMaisVendidos = "select itempedido.idproduto,sum(quantidade) as soma, preco, titulo, autor, idimg, fkgenero, fkeditora, genero.nome as generonome, editora.nome as editoranome from itempedido left join produto on (produto.idproduto = itempedido.idproduto) inner join genero on (produto.fkgenero = genero.idgenero) inner join editora on (produto.fkeditora = editora.ideditora) where produto.inativo = false group by itempedido.idproduto, autor, produto.titulo,preco,idimg,fkgenero, fkeditora,generonome, editoranome order by soma desc limit 12";
    private final String stmtListaProdutosMaisVendidosAZ = "select * from(select itempedido.idproduto,sum(quantidade) as soma, preco, titulo, autor, idimg, fkgenero, fkeditora, genero.nome as generonome, editora.nome as editoranome from itempedido left join produto on (produto.idproduto = itempedido.idproduto) inner join genero on (produto.fkgenero = genero.idgenero) inner join editora on (produto.fkeditora = editora.ideditora) where produto.inativo = false group by itempedido.idproduto, autor, produto.titulo,preco,idimg,fkgenero, fkeditora,generonome, editoranome order by soma desc limit 12) as tabela order by titulo asc";
    private final String stmtListaProdutosMaisVendidosZA = "select * from(select itempedido.idproduto,sum(quantidade) as soma, preco, titulo, autor, idimg, fkgenero, fkeditora, genero.nome as generonome, editora.nome as editoranome from itempedido left join produto on (produto.idproduto = itempedido.idproduto) inner join genero on (produto.fkgenero = genero.idgenero) inner join editora on (produto.fkeditora = editora.ideditora) where produto.inativo = false group by itempedido.idproduto, autor, produto.titulo,preco,idimg,fkgenero, fkeditora,generonome, editoranome order by soma desc limit 12) as tabela order by titulo desc";
    private final String stmtListaProdutosMaisVendidosAsc = "select * from(select itempedido.idproduto,sum(quantidade) as soma, preco, titulo, autor, idimg, fkgenero, fkeditora, genero.nome as generonome, editora.nome as editoranome from itempedido left join produto on (produto.idproduto = itempedido.idproduto) inner join genero on (produto.fkgenero = genero.idgenero) inner join editora on (produto.fkeditora = editora.ideditora) where produto.inativo = false group by itempedido.idproduto, autor, produto.titulo,preco,idimg,fkgenero, fkeditora,generonome, editoranome order by soma desc limit 12) as tabela order by preco asc";
    private final String stmtListaProdutosMaisVendidosDesc = "select * from(select itempedido.idproduto,sum(quantidade) as soma, preco, titulo, autor, idimg, fkgenero, fkeditora, genero.nome as generonome, editora.nome as editoranome from itempedido left join produto on (produto.idproduto = itempedido.idproduto) inner join genero on (produto.fkgenero = genero.idgenero) inner join editora on (produto.fkeditora = editora.ideditora) where produto.inativo = false group by itempedido.idproduto, autor, produto.titulo,preco,idimg,fkgenero, fkeditora,generonome, editoranome order by soma desc limit 12) as tabela order by preco desc";
//

    public int cadastrarProduto(Produto p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtCadastraProduto, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getAutor());
            stmt.setInt(3, p.getEditora().getIdEditora());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getGenero().getIdGenero());
            stmt.executeUpdate();
            con.commit();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt("idimg");
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
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
            stmt.setInt(3, p.getEditora().getIdEditora());
            stmt.setDouble(4, p.getPreco());
            stmt.setString(5, p.getGenero().getNome());
            stmt.setInt(6, p.getIdProduto());
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
                System.out.println("Erro ao fechar a conexao. Ex =" + ex.getMessage());
            }
        }
    }

    public void removerProduto(Produto p) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtRemoveProduto);
            stmt.setInt(1, p.getIdProduto());
            stmt.executeUpdate();
            con.commit();
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

    private final String stmtBuscaProdutoPorId = "select * from produto where idProduto = ? and inativo = false";
//nao retorna genero e editora

    public Produto listarProdutoPorId(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtBuscaProdutoPorId);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("idProduto"));
            produto.setTitulo(rs.getString("titulo"));
            produto.setAutor(rs.getString("autor"));
            produto.setPreco(rs.getDouble("preco"));
            return produto;

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

    public List<Produto> listarProduto() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaProduto);
            rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setTitulo(rs.getString("titulo"));
                listaProdutos.add(produto);
            }
            return listaProdutos;

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

    public List<Produto> listarProdutosMaisVendidos() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaProdutosMaisVendidos);
            rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setidImg(rs.getInt("idimg"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("fkGenero"));
                genero.setNome(rs.getString("generoNome"));
                produto.setGenero(genero);

                Editora editora = new Editora();
                editora.setIdEditora(rs.getInt("fkEditora"));
                editora.setNome(rs.getString("editoraNome"));
                produto.setEditora(editora);

                listaProdutos.add(produto);
            }
            return listaProdutos;

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
    
    public List<Produto> listarProdutosMaisVendidosAZ() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaProdutosMaisVendidosAZ);
            rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setidImg(rs.getInt("idimg"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("fkGenero"));
                genero.setNome(rs.getString("generoNome"));
                produto.setGenero(genero);

                Editora editora = new Editora();
                editora.setIdEditora(rs.getInt("fkEditora"));
                editora.setNome(rs.getString("editoraNome"));
                produto.setEditora(editora);

                listaProdutos.add(produto);
            }
            return listaProdutos;

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
    
    public List<Produto> listarProdutosMaisVendidosZA() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaProdutosMaisVendidosZA);
            rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setidImg(rs.getInt("idimg"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("fkGenero"));
                genero.setNome(rs.getString("generoNome"));
                produto.setGenero(genero);

                Editora editora = new Editora();
                editora.setIdEditora(rs.getInt("fkEditora"));
                editora.setNome(rs.getString("editoraNome"));
                produto.setEditora(editora);

                listaProdutos.add(produto);
            }
            return listaProdutos;

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
    public List<Produto> listarProdutosMaisVendidosAsc() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaProdutosMaisVendidosAsc);
            rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setidImg(rs.getInt("idimg"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("fkGenero"));
                genero.setNome(rs.getString("generoNome"));
                produto.setGenero(genero);

                Editora editora = new Editora();
                editora.setIdEditora(rs.getInt("fkEditora"));
                editora.setNome(rs.getString("editoraNome"));
                produto.setEditora(editora);

                listaProdutos.add(produto);
            }
            return listaProdutos;

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
    public List<Produto> listarProdutosMaisVendidosDesc() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListaProdutosMaisVendidosDesc);
            rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setidImg(rs.getInt("idimg"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("fkGenero"));
                genero.setNome(rs.getString("generoNome"));
                produto.setGenero(genero);

                Editora editora = new Editora();
                editora.setIdEditora(rs.getInt("fkEditora"));
                editora.setNome(rs.getString("editoraNome"));
                produto.setEditora(editora);

                listaProdutos.add(produto);
            }
            return listaProdutos;

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
