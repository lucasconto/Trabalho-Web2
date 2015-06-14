package br.com.magazine.entidade;

import java.sql.Timestamp;
import java.util.List;

public class Pedido {

    int idPedido;
    int idCliente;
    List<Itempedido> itens;
    int status;
    Timestamp data;
    double valorTotal;

    public List<Itempedido> getItens() {
        return itens;
    }

    public void setItens(List<Itempedido> itens) {
        this.itens = itens;
    }

    
    
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
