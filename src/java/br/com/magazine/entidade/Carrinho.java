package br.com.magazine.entidade;

import java.util.List;

public class Carrinho {

    double total;
    List<Itempedido> itens;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Itempedido> getItens() {
        return itens;
    }

    public void setItens(List<Itempedido> itens) {
        this.itens = itens;
    }

  
    public int getNumeroItens() {
        return itens.isEmpty() ? 0 : itens.size();
    }
    
    
}
