/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.teste;

import br.com.magazine.dao.PedidoDAO;
import br.com.magazine.entidade.Cliente;
import br.com.magazine.entidade.Genero;
import br.com.magazine.entidade.ItemPedido;
import br.com.magazine.entidade.Pedido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Evandro
 */
public class TestePedidoDAO {
    public static void main (String[] args) throws SQLException, ClassNotFoundException{
        Cliente cliente = new Cliente();
        cliente.setIdCliente(26);
        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Pedido> listaPedidos = pedidoDAO.listaPedidosCliente(cliente);
        for(Pedido pedido : listaPedidos){
            for(ItemPedido itemPedido : pedido.getItens())
                System.out.println(itemPedido.getProduto().getTitulo());
        }
    }
}
