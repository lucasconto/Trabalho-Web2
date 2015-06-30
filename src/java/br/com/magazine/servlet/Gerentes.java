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
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Evandro-PC
 */
@WebServlet(name = "Gerentes", urlPatterns = {"/gerente/Gerentes"})
public class Gerentes extends HttpServlet {

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
        if ("maisVendido".equals(request.getParameter("action"))) {
            String de = request.getParameter("de");
            String ate = request.getParameter("ate");
            de = dateFormat(de);
            ate = dateFormat(ate);
            Connection con = null;
            try {
                con = ConnectionFactory.getConnection();

                // Caminho físico do relatório compilado
                String jasper = request.getContextPath() + "/relatorios/maisVendidos.jasper";

                // Host onde o servlet esta executando 
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("de", de);
                params.put("ate", ate);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");

                    // Envia o PDF para o Cliente
                    OutputStream ops = null;
                    ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                // erro de driver
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (SQLException e) {
                // erro de SQL
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (JRException e) {
                // erro de Jasper
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                    }
                }
            }
        } else if ("compras".equals(request.getParameter("action"))) {

            HttpSession session = request.getSession();
            Integer logado;
            try {
                logado = (int) session.getAttribute("logado");
            } catch (Exception f) {
                logado = 0;
            }
            if (logado > 0) {
                int perfil = (int) session.getAttribute("perfil");
                if (perfil < 3) {
                    response.sendRedirect("../gerente/semPermissao.jsp");
                } else {

                    Connection con = null;
                    try {
                        con = ConnectionFactory.getConnection();
                        // Caminho físico do relatório compilado
                        String jasper = request.getContextPath() + "/relatorios/compras.jasper";
                        // Host onde o servlet esta executando 
                        String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                        // URL para acesso ao relatório
                        URL jasperURL = new URL(host + jasper);
                        HashMap params = new HashMap();
                        byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                        if (bytes != null) {
                            // A página será mostrada em PDF
                            response.setContentType("application/pdf");

                            // Envia o PDF para o Cliente
                            OutputStream ops = null;
                            ops = response.getOutputStream();
                            ops.write(bytes);
                        }
                    } catch (ClassNotFoundException e) {
                        // erro de driver
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><head>");
                        out.println("<title>Servlet Gerentes</title>");
                        out.println("</head><body>");
                        out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                        out.println("</body></html>");
                        out.flush();
                    } catch (SQLException e) {
                        // erro de SQL
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><head>");
                        out.println("<title>Servlet Gerentes</title>");
                        out.println("</head><body>");
                        out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                        out.println("</body></html>");
                        out.flush();
                    } catch (JRException e) {
                        // erro de Jasper
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><head>");
                        out.println("<title>Servlet Gerentes</title>");
                        out.println("</head><body>");
                        out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                        out.println("</body></html>");
                        out.flush();
                    } finally {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (Exception e) {
                            }
                        }
                    }

                }
            } else {
                response.sendRedirect("../comum/login.jsp");
            }

        } else if ("perfilCliente".equals(request.getParameter("action"))) {

            HttpSession session = request.getSession();
            Integer logado;
            try {
                logado = (int) session.getAttribute("logado");
            } catch (Exception f) {
                logado = 0;
            }
            if (logado > 0) {
                int perfil = (int) session.getAttribute("perfil");
                if (perfil < 3) {
                    response.sendRedirect("../gerente/semPermissao.jsp");
                } else {

                    Connection con = null;
                    try {
                        con = ConnectionFactory.getConnection();

                        // Caminho físico do relatório compilado
                        String jasper = request.getContextPath() + "/relatorios/perfilCliente.jasper";

                        // Host onde o servlet esta executando 
                        String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                        // URL para acesso ao relatório
                        URL jasperURL = new URL(host + jasper);

                        HashMap params = new HashMap();
                        byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                        if (bytes != null) {
                            // A página será mostrada em PDF
                            response.setContentType("application/pdf");

                            // Envia o PDF para o Cliente
                            OutputStream ops = null;
                            ops = response.getOutputStream();
                            ops.write(bytes);
                        }
                    } catch (ClassNotFoundException e) {
                        // erro de driver
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><head>");
                        out.println("<title>Servlet Gerentes</title>");
                        out.println("</head><body>");
                        out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                        out.println("</body></html>");
                        out.flush();
                    } catch (SQLException e) {
                        // erro de SQL
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><head>");
                        out.println("<title>Servlet Gerentes</title>");
                        out.println("</head><body>");
                        out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                        out.println("</body></html>");
                        out.flush();
                    } catch (JRException e) {
                        // erro de Jasper
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><head>");
                        out.println("<title>Servlet Gerentes</title>");
                        out.println("</head><body>");
                        out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                        out.println("</body></html>");
                        out.flush();
                    } finally {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (Exception e) {
                            }
                        }
                    }

                }
            } else {
                response.sendRedirect("../comum/login.jsp");
            }

        } else if ("faturamentoMensal".equals(request.getParameter("action"))) {
            String mes = request.getParameter("mes");
            String ano = request.getParameter("ano");
            Connection con = null;
            try {
                con = ConnectionFactory.getConnection();

                // Caminho físico do relatório compilado
                String jasper = request.getContextPath() + "/relatorios/faturamentoMensal.jasper";

                // Host onde o servlet esta executando 
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("mes", mes);
                params.put("ano", ano);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");

                    // Envia o PDF para o Cliente
                    OutputStream ops = null;
                    ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                // erro de driver
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (SQLException e) {
                // erro de SQL
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (JRException e) {
                // erro de Jasper
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                    }
                }
            }
        } else if ("faturamentoAnual".equals(request.getParameter("action"))) {
            String ano = request.getParameter("ano");
            Connection con = null;
            try {
                con = ConnectionFactory.getConnection();

                // Caminho físico do relatório compilado
                String jasper = request.getContextPath() + "/relatorios/faturamentoAnual.jasper";

                // Host onde o servlet esta executando 
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("ano", ano);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");

                    // Envia o PDF para o Cliente
                    OutputStream ops = null;
                    ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                // erro de driver
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (SQLException e) {
                // erro de SQL
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (JRException e) {
                // erro de Jasper
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                    }
                }
            }
        } else if ("topBuyers".equals(request.getParameter("action"))) {
            String de = request.getParameter("de");
            String ate = request.getParameter("ate");
            de = dateFormat(de);
            ate = dateFormat(ate);
            Connection con = null;
            try {
                con = ConnectionFactory.getConnection();

                // Caminho físico do relatório compilado
                String jasper = request.getContextPath() + "/relatorios/topBuyers.jasper";

                // Host onde o servlet esta executando 
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("inicio", de);
                params.put("fim", ate);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");

                    // Envia o PDF para o Cliente
                    OutputStream ops = null;
                    ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                // erro de driver
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (SQLException e) {
                // erro de SQL
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (JRException e) {
                // erro de Jasper
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
        else if ("compraPeriodo".equals(request.getParameter("action"))) {
            String de = request.getParameter("de");
            String ate = request.getParameter("ate");
            de = dateFormat(de);
            ate = dateFormat(ate);
            Connection con = null;
            try {
                con = ConnectionFactory.getConnection();

                // Caminho físico do relatório compilado
                String jasper = request.getContextPath() + "/relatorios/compraPeriodo.jasper";

                // Host onde o servlet esta executando 
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("inicio", de);
                params.put("fim", ate);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");

                    // Envia o PDF para o Cliente
                    OutputStream ops = null;
                    ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                // erro de driver
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Driver (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (SQLException e) {
                // erro de SQL
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de SQL (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } catch (JRException e) {
                // erro de Jasper
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet Gerentes</title>");
                out.println("</head><body>");
                out.println("<h1>Erro de Jasper (" + e.getMessage() + ") no Servlet Gerentes at " + request.getContextPath() + "</h1>");
                out.println("</body></html>");
                out.flush();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
        else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                if ("cadastrar".equals(request.getParameter("action"))) {
                    Cliente cliente = new Cliente();

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
                    cliente.setInativo(Boolean.parseBoolean(request.getParameter("perfil")));

                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.cadastrarCliente(cliente);
                    response.sendRedirect("./buscarFuncionario.jsp");
                    return;
                }
                if ("buscarf".equals(request.getParameter("action"))) {
                    List<Cliente> listaClientes = new ArrayList();
                    ClienteDAO clienteDAO = new ClienteDAO();
                    String escolha = request.getParameter("escolha");
                    String str = request.getParameter("str");
                    if ("nome".equals(escolha)) {
                        listaClientes = clienteDAO.buscarFuncionarioNome(str);
                    } else if ("cpf".equals(escolha)) {
                        listaClientes = clienteDAO.buscarFuncionarioCPF(str);
                    } else if ("email".equals(escolha)) {
                        listaClientes = clienteDAO.buscarFuncionarioEmail(str);
                    }
                    request.setAttribute("listaClientes", listaClientes);
                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/listarFuncionario.jsp");
                    rd.forward(request, response);
                }
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
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/listarCliente.jsp");
                    rd.forward(request, response);
                }
                if ("visualizarf".equals(request.getParameter("action"))) {
                    String escolha = request.getParameter("escolha");
                    String str = request.getParameter("str");
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente clienteSessao = new Cliente();
                    clienteSessao.setIdCliente(Integer.parseInt(request.getParameter("id")));
                    Cliente cliente = clienteDAO.buscarClienteId(clienteSessao);
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/visualizarFuncionario.jsp");
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
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/listarComprasCliente.jsp");
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
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/visualizarCliente.jsp");
                    rd.forward(request, response);
                }
                if ("valterarf".equals(request.getParameter("action"))) {
                    String escolha = request.getParameter("escolha");
                    String str = request.getParameter("str");
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente clienteSessao = new Cliente();
                    clienteSessao.setIdCliente(Integer.parseInt(request.getParameter("id")));
                    Cliente cliente = clienteDAO.buscarClienteId(clienteSessao);
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/alterarFuncionario.jsp");
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
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/alterarCliente.jsp");
                    rd.forward(request, response);
                }
                if ("alterarf".equals(request.getParameter("action"))) {
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
                    cliente.setPerfil(Integer.parseInt(request.getParameter("perfil")));

                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.atualizarCliente(cliente);
                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/Gerentes?action=buscarf");
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
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/Gerentes?action=buscarc");
                    rd.forward(request, response);
                }
                if ("excluirf".equals(request.getParameter("action"))) {
                    String escolha = request.getParameter("escolha");
                    String str = request.getParameter("str");
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(Integer.parseInt(request.getParameter("id")));
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.removerCliente(cliente);
                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/Gerentes?action=buscarf");
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
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/Gerentes?action=buscarc");
                    rd.forward(request, response);
                }
                if ("visualizarAcoes".equals(request.getParameter("action"))) {
                    String escolha = request.getParameter("escolha");
                    String str = request.getParameter("str");
                    String nome = request.getParameter("nome");
                    String de = request.getParameter("de");
                    String ate = request.getParameter("ate");
                    int id = Integer.parseInt(request.getParameter("id"));
                    LogDAO logDAO = new LogDAO();
                    List<Log> listaLogs = logDAO.listarLogs(id, de, ate);

                    request.setAttribute("escolha", escolha);
                    request.setAttribute("str", str);
                    request.setAttribute("id", id);
                    request.setAttribute("nome", nome);
                    request.setAttribute("listaLogs", listaLogs);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente/listarAcoesCliente.jsp");
                    rd.forward(request, response);
                    return;
                }
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
            Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
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
