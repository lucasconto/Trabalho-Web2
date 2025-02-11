/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.servlet;

import br.com.magazine.dao.ClienteDAO;
import br.com.magazine.entidade.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "Login", urlPatterns = {"/comum/Login"})
public class Login extends HttpServlet {

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
            throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if ("login".equals(request.getParameter("action"))) {

                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
//                if (email.isEmpty() || senha.isEmpty()) {
//                    request.setAttribute("mensagem", "Informe usuário e senha válidos");
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/comum/login.jsp");
//                    rd.forward(request, response);
//                }

                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = new Cliente();
                cliente = clienteDAO.buscarClientePorEmail(email);
                if (cliente == null) {
                    //erro, não achou o cliente no banco
                    request.setAttribute("mensagem", "Email Inválido! Cliente não encontrado na base de dados.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/comum/login.jsp");
                    rd.forward(request, response);
                } else {
                    String senhaCliente = cliente.getSenha();
                    if (!senhaCliente.isEmpty() && senha.equals(senhaCliente)) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("cliente", cliente);
                        session.setAttribute("nome", cliente.getNome());
                        session.setAttribute("idcliente", cliente.getIdCliente());
                        session.setAttribute("logado", 1);
                        session.setAttribute("perfil", cliente.getPerfil());
                        if (cliente.getPerfil() == 1) {
                            response.sendRedirect("../cliente/Clientes");
                        } else if (cliente.getPerfil() == 2) {
                            response.sendRedirect("../administrador/buscarCliente.jsp");
                        } else if (cliente.getPerfil() == 3) {
                            response.sendRedirect("../gerente/buscarCliente.jsp");
                        }
//                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cliente/index.jsp");
//                        rd.forward(request, response);
                    } else {
                        //erro: senha invalida
                        request.setAttribute("mensagem", "Senha inválida!");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/comum/login.jsp");
                        rd.forward(request, response);
                    }
                }
            }
            if ("logout".equals(request.getParameter("action"))) {
                HttpSession session = request.getSession();
                //if (session != null) {
                session.invalidate();
                //}
                response.sendRedirect("../cliente/Clientes");
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
        } catch (ParseException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
