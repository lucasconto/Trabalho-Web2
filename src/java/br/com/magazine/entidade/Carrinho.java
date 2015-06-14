package br.com.magazine.entidade;

import java.util.List;

public class Carrinho {

    double total;
    List<ItemPedido> itens;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

  
    public int getNumeroItens() {
        return itens.isEmpty() ? 0 : itens.size();
    }
    
    
}
