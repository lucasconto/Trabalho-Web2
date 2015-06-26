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
public class Produtos extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
    response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ("cadastrar".equals(request.getParameter("action"))) {
                Part filePart = request.getPart("idImg"); // Retrieves <input type="file" name="file">
                
                ProdutoDAO dao = new ProdutoDAO();
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
                String nomeArquivo = dao.cadastrarProduto(produto)+ ".jpg"; 
                File homedir = new File(System.getProperty("user.home"));
                File file = new File(homedir+"/teste", nomeArquivo); 
                
               
                try (InputStream input = filePart.getInputStream()) {  // How to obtain part is answered in http://stackoverflow.com/a/2424824
                    Files.copy(input, file.toPath());
                }
                
                response.sendRedirect("./cadastrarProduto.jsp");
                return;
    
                
      
            }
            
                if ("cadastrarProduto".equals(request.getParameter("action"))) {
                List<Editora> listaEditoras = new ArrayList();
                EditoraDAO editoraDAO = new EditoraDAO();
                listaEditoras = editoraDAO.listarEditoras();
                request.setAttribute("listaEditoras", listaEditoras);
                
                
                List<Genero> listaGeneros = new ArrayList();
                GeneroDAO generoDAO = new GeneroDAO();
                listaGeneros = generoDAO.listarGeneros();
                
                request.setAttribute("listaGeneros", listaGeneros);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/cadastrarProduto.jsp");
                rd.forward(request,response);
                
                
                }
            
            
                if ("buscarp".equals(request.getParameter("action"))) {
                    List<Produto> listaProdutos = new ArrayList();
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    String escolha = request.getParameter("escolha");
                    String str = request.getParameter("str");
                    if("editora".equals(escolha)){
                        listaProdutos = produtoDAO.buscarClienteNome(str);
                    } else if("titulo".equals(escolha)){
                        listaProdutos = produtoDAO.buscarClienteNome(str);
                    } else if("genero".equals(escolha)){
                        listaProdutos = produtoDAO.buscarClienteNome(str);
                    }
                    request.setAttribute("listaProdutos", listaProdutos);
                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/listarProduto.jsp");
                    rd.forward(request,response);
                }
                

            
            
            if ("buscarpp".equals(request.getParameter("action"))) {
                List<Produto> listaProdutos = new ArrayList();
                ProdutoDAO produtoDAO = new ProdutoDAO();
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                   
                if("titulo".equals(escolha)){
                    out.println(str);
               //     listaProdutos = produtoDAO.listarProdutoPorNome(str);

                
                }
                request.setAttribute("listaProdutos", listaProdutos);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
           //     RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/visualizarProduto.jsp");
            //    rd.forward(request,response);
            
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
        
      
