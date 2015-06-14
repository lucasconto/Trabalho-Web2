/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.servlet;

import br.com.magazine.dao.ProdutoDAO;
import br.com.magazine.entidade.Carrinho;
import br.com.magazine.entidade.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Evandro-PC
 */
@WebServlet(name = "Carrinhos", urlPatterns = {"/Carrinhos"})
public class Carrinhos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ("addCarrinho".equals(request.getParameter("action"))) {
                int idProduto = Integer.parseInt(request.getParameter("id"));
                HttpSession session = request.getSession();
                Carrinho carrinho = (Carrinho)session.getAttribute("carrinho");
                boolean logado = (boolean)session.getAttribute("logado");
                out.print("entrou nessa linha");
                
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = new Produto();
                //produto = produtoDAO.listarProdutoPorId(idProduto);
                produto.setIdProduto(1);
                produto.setTitulo("zuera never ends");
                produto.setPreco(199);
                
                if (carrinho == null) {
                    carrinho = new Carrinho();
                }
                carrinho.adicionarItem(produto);
                carrinho.incrementaTotal(produto.getPreco());
                session.setAttribute("carrinho", carrinho);
                if (logado) {
                    response.sendRedirect("cliente/index.jsp");
                } else {
                    response.sendRedirect("comum/index.jsp");
                }

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Carrinhos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carrinhos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Carrinhos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carrinhos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
