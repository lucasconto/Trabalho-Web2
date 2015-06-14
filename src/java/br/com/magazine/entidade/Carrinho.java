package br.com.magazine.entidade;

import java.util.List;

public class Carrinho {

    double total;
    List<Produto> itens;

    public void incrementaTotal(double total) {
        this.total += total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }

    public void adicionarItem(Produto produto) {
        itens.add(produto);
    }

    public int getNumeroItens() {
        return itens.isEmpty() ? 0 : itens.size();
    }

}
