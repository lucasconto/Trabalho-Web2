package br.com.magazine.servlet;

import br.com.magazine.dao.EditoraDAO;
import br.com.magazine.dao.GeneroDAO;
import br.com.magazine.dao.ProdutoDAO;
import br.com.magazine.entidade.Editora;
import br.com.magazine.entidade.Genero;
import br.com.magazine.entidade.Produto;
import br.com.magazine.util.ConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anonymous-pc
 */
@WebServlet(name = "Produtos", urlPatterns = {"/administrador/Produtos"})
@MultipartConfig
public class Produtos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ("cadastrar".equals(request.getParameter("action"))) {
                Part filePart = request.getPart("idImg"); // Retrieves <input type="file" name="file">

                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = new Produto();
                Genero genero = new Genero();
                Editora editora = new Editora();

                produto.setTitulo(request.getParameter("titulo"));
                produto.setAutor(request.getParameter("autor"));
                editora.setIdEditora(Integer.parseInt(request.getParameter("editora")));
                produto.setEditora(editora);
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                genero.setIdGenero(Integer.parseInt(request.getParameter("genero")));
                produto.setGenero(genero);

//                ----Salvando em um diretorio no servidor----
                String nomeArquivo = produtoDAO.cadastrarProduto(produto) + ".jpg";
                File homedir = new File(System.getProperty("user.home"));
                File file = new File(homedir + "/teste", nomeArquivo);

                try (InputStream input = filePart.getInputStream()) {  // How to obtain part is answered in http://stackoverflow.com/a/2424824
                    Files.copy(input, file.toPath());
                }

                response.sendRedirect("./cadastrarProduto.jsp");
                return;

            }

            if ("cadastrarProduto".equals(request.getParameter("action"))) {
                HttpSession session = request.getSession();
                Integer logado;
                try {
                    logado = (int) session.getAttribute("logado");
                } catch (Exception f) {
                    logado = 0;
                }
                if (logado > 0) {
                    int perfil = (int) session.getAttribute("perfil");
                    if (perfil < 2) {
                        response.sendRedirect("../administrador/semPermissao.jsp");
                    } else {

                        List<Editora> listaEditoras = new ArrayList();
                        EditoraDAO editoraDAO = new EditoraDAO();
                        listaEditoras = editoraDAO.listarEditoras();
                        request.setAttribute("listaEditoras", listaEditoras);

                        List<Genero> listaGeneros = new ArrayList();
                        GeneroDAO generoDAO = new GeneroDAO();
                        listaGeneros = generoDAO.listarGeneros();

                        request.setAttribute("listaGeneros", listaGeneros);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/cadastrarProduto.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    response.sendRedirect("../comum/login.jsp");
                }

            }

            if ("buscarp".equals(request.getParameter("action"))) {
                List<Produto> listaProdutos = new ArrayList();
                ProdutoDAO produtoDAO = new ProdutoDAO();
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                if ("autor".equals(escolha)) {
                    listaProdutos = produtoDAO.buscarAutorProduto(str);
                } else if ("titulo".equals(escolha)) {
                    listaProdutos = produtoDAO.buscarTituloProduto(str);
                } else if ("genero".equals(escolha)) {
                    listaProdutos = produtoDAO.buscarGeneroProduto(str);
                }
                request.setAttribute("listaProdutos", listaProdutos);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/listarProduto.jsp");
                rd.forward(request, response);
            }

            if ("excluirp".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                Produto produto = new Produto();
                produto.setIdProduto(Integer.parseInt(request.getParameter("id")));
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.removerProduto(produto);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/Produtos?action=buscarp");
                rd.forward(request, response);
            }

            if ("visualizarp".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Produto produto = produtoDAO.buscarProdutoPorId(id);
                request.setAttribute("produto", produto);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/visualizarProduto.jsp");
                rd.forward(request, response);
            }
            if ("valterarp".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Produto produto = produtoDAO.buscarProdutoPorId(id);
                request.setAttribute("produto", produto);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                List<Editora> listaEditoras = new ArrayList();
                EditoraDAO editoraDAO = new EditoraDAO();
                listaEditoras = editoraDAO.listarEditoras();
                request.setAttribute("listaEditoras", listaEditoras);
                List<Genero> listaGeneros = new ArrayList();
                GeneroDAO generoDAO = new GeneroDAO();
                listaGeneros = generoDAO.listarGeneros();
                request.setAttribute("listaGeneros", listaGeneros);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/alterarProduto.jsp");
                rd.forward(request, response);
            }
            if ("alterarp".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");

                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = new Produto();
                Genero genero = new Genero();
                Editora editora = new Editora();
                produto.setTitulo(request.getParameter("titulo"));
                produto.setAutor(request.getParameter("autor"));
                editora.setIdEditora(Integer.parseInt(request.getParameter("editora")));
                produto.setEditora(editora);
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                genero.setIdGenero(Integer.parseInt(request.getParameter("genero")));
                produto.setGenero(genero);
                produto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));
                produtoDAO.atualizarProduto(produto);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/Produtos?action=buscarp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
