/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.servlet;

import br.com.magazine.dao.GeneroDAO;
import br.com.magazine.entidade.Genero;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Yuri
 */
@WebServlet(name = "Generos", urlPatterns = {"/administrador/Generos"})
public class Generos extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if ("cadastrar".equals(request.getParameter("action"))) {
                Genero genero = new Genero();
                genero.setNome(request.getParameter("genero"));
                System.out.println(request.getParameter("genero"));
                GeneroDAO gdao = new GeneroDAO();
                gdao.cadastrarGenero(genero);
            }
            if ("editar".equals(request.getParameter("action"))) {
                Genero genero = new Genero();
                genero.setIdGenero(Integer.parseInt(request.getParameter("idgenero")));
                genero.setNome(request.getParameter("genero"));
                GeneroDAO gdao = new GeneroDAO();
                gdao.atualizarGenero(genero);
                response.sendRedirect("./Generos");
                return;
            }
            if ("remover".equals(request.getParameter("action"))) {
                Genero genero = new Genero();
                genero.setIdGenero(Integer.parseInt(request.getParameter("id")));
                GeneroDAO generoDAO = new GeneroDAO();
                generoDAO.removerGenero(genero);
                response.sendRedirect("./Generos");
            } else {
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
                        List<Genero> listaGeneros = new ArrayList();
                        GeneroDAO generoDAO = new GeneroDAO();
                        listaGeneros = generoDAO.listarGeneros();

                        request.setAttribute("generoLista", listaGeneros);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/cadastrarGenero.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    response.sendRedirect("../comum/login.jsp");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Generos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Generos.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Generos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Generos.class.getName()).log(Level.SEVERE, null, ex);
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
