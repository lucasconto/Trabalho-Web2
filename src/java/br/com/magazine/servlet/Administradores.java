/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.servlet;

import br.com.magazine.dao.ClienteDAO;
import br.com.magazine.dao.EditoraDAO;
import br.com.magazine.dao.GeneroDAO;
import br.com.magazine.dao.LogDAO;
import br.com.magazine.dao.PedidoDAO;
import br.com.magazine.entidade.Cliente;
import br.com.magazine.entidade.Editora;
import br.com.magazine.entidade.Genero;
import br.com.magazine.entidade.Log;
import br.com.magazine.entidade.Pedido;
import br.com.magazine.util.ConnectionFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Evandro-PC
 */
@WebServlet(name = "Administradores", urlPatterns = {"/administrador/Administradores"})
public class Administradores extends HttpServlet {

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
            if ("buscarc".equals(request.getParameter("action"))) {
                List<Cliente> listaClientes = new ArrayList();
                ClienteDAO clienteDAO = new ClienteDAO();
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                if ("nome".equals(escolha)) {
                    listaClientes = clienteDAO.buscarClienteNome(str);
                } else if ("cpf".equals(escolha)) {
                    listaClientes = clienteDAO.buscarClienteCPF(str);
                } else if ("email".equals(escolha)) {
                    listaClientes = clienteDAO.buscarClienteEmail(str);
                }
                request.setAttribute("listaClientes", listaClientes);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/listarCliente.jsp");
                rd.forward(request, response);
            }
            if ("visualizarCompras".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente clienteSessao = new Cliente();
                clienteSessao.setIdCliente(Integer.parseInt(request.getParameter("id")));
                Cliente cliente = clienteDAO.buscarClienteId(clienteSessao);
                PedidoDAO pedidoDAO = new PedidoDAO();
                List<Pedido> listaPedidos = pedidoDAO.listaItensPedidosCliente(cliente);
                request.setAttribute("cliente", cliente);
                request.setAttribute("listaPedidos", listaPedidos);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/listarComprasCliente.jsp");
                rd.forward(request, response);
            }
            if ("visualizarc".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente clienteSessao = new Cliente();
                clienteSessao.setIdCliente(Integer.parseInt(request.getParameter("id")));
                Cliente cliente = clienteDAO.buscarClienteId(clienteSessao);
                request.setAttribute("cliente", cliente);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/visualizarCliente.jsp");
                rd.forward(request, response);
            }
            if ("valterarc".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente clienteSessao = new Cliente();
                clienteSessao.setIdCliente(Integer.parseInt(request.getParameter("id")));
                Cliente cliente = clienteDAO.buscarClienteId(clienteSessao);
                request.setAttribute("cliente", cliente);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/alterarCliente.jsp");
                rd.forward(request, response);
            }
            if ("alterarc".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                Cliente cliente = new Cliente();

                cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                cliente.setNome(request.getParameter("nome"));
                cliente.setSexo(request.getParameter("sexo"));
                cliente.setCpf(request.getParameter("cpf"));

                String nascimentoStr = request.getParameter("nascimento");
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date nascimentoUtil = format.parse(nascimentoStr);
                java.sql.Date nascimentoSql = new java.sql.Date(nascimentoUtil.getTime());
                cliente.setNascimento(nascimentoSql);

                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setSenha(request.getParameter("senha"));
                cliente.setCep(request.getParameter("cep"));
                cliente.setEndereco(request.getParameter("endereco"));
                cliente.setEndNumero(request.getParameter("numero"));
                cliente.setEndComplemento(request.getParameter("complemento"));
                cliente.setBairro(request.getParameter("bairro"));
                cliente.setCidade(request.getParameter("cidade"));
                cliente.setEstado(request.getParameter("estado"));
                cliente.setPerfil(1);

                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.atualizarCliente(cliente);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/Administradores?action=buscarc");
                rd.forward(request, response);
            }
            if ("excluirc".equals(request.getParameter("action"))) {
                String escolha = request.getParameter("escolha");
                String str = request.getParameter("str");
                Cliente cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(request.getParameter("id")));
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.removerCliente(cliente);
                request.setAttribute("escolha", escolha);
                request.setAttribute("str", str);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/administrador/Administradores?action=buscarc");
                rd.forward(request, response);
            }
        }
    }

    private String dateFormat(String date) {
        //this function will change the Date String format from dd/mm/yyyy to yyyy-mm-dd
        String day, month, year;
        day = date.substring(0, 2);
        month = date.substring(3, 5);
        year = date.substring(6, 10);
        date = year + "-" + month + "-" + day;
        return date;
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
            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
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
