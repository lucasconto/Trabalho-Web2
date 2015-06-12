package br.com.magazine.servlet;

import br.com.magazine.dao.EditoraDAO;
import br.com.magazine.dao.ProdutoDAO;
import br.com.magazine.entidade.Editora;
import br.com.magazine.entidade.Genero;
import br.com.magazine.entidade.Produto;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anonymous-pc
 */
@WebServlet(name = "Produtos", urlPatterns = {"/Produtos"})
@MultipartConfig
public class Produtos extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException, ClassNotFoundException {
    response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ("cadastrar".equals(request.getParameter("action"))) {
                Part filePart = request.getPart("idImg"); // Retrieves <input type="file" name="file">
                
                ProdutoDAO dao = new ProdutoDAO();
                Produto produto = new Produto();
                Genero genero = new Genero();
                genero.setIdGenero(Integer.parseInt(request.getParameter("genero")));
                produto.setGenero(genero);
                Editora editora = new Editora();
                editora.setIdEditora(Integer.parseInt(request.getParameter("editora")));
                produto.setEditora(editora);
                produto.setTitulo("teste");            
               
                             
//                ----Salvando em um diretorio no servidor----
                String nomeArquivo = dao.cadastrarProduto(produto)+ ".jpg";           
                File homedir = new File(System.getProperty("user.home"));
                File file = new File(homedir+"\\teste", nomeArquivo); 
                
                //----Salvando no projeto do servidor--------
//                String nomeArquivo = dao.cadastrarProduto(produto)+ ".jpg";     
//                String caminho = request.getServletContext().getRealPath("");
//                String caminhoSalvar = caminho + File.separator + "imagemLivros";
//                File uploads = new File("/imagemLivros");
//                File file = new File(caminhoSalvar, nomeArquivo); 
                

                
                
                
                
                try (InputStream input = filePart.getInputStream()) {  // How to obtain part is answered in http://stackoverflow.com/a/2424824
                    Files.copy(input, file.toPath());
                }
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Produtos</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Produtos at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
              //  Produto produto = new Produto();
              //  Genero genero = new Genero();
//                Editora editora = new Editora();
//                EditoraDAO editoraDAO = new EditoraDAO();
//                editora = editoraDAO.buscarIdEditora(editora);
//                
//                editora.setNome(request.getParameter("editora"));
//                genero.setNome(request.getParameter("genero"));
                

                produto.setTitulo(request.getParameter("titulo"));
                produto.setAutor(request.getParameter("autor"));
//                produto.setEditora(editora);
                produto.setGenero(genero);
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                
               
                //sem imagem
                //produto.setidImg(Integer.parseInt(request.getParameter("idImg")));                
             
                
      
            }
        }
    }
        
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
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
        
      
