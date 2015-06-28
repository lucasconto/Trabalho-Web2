package br.com.magazine.entidade;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    double total;
    List<ItemPedido> listaItens;

    public void incrementaTotal() {
        double soma = 0;
        for (ItemPedido item : this.listaItens) {
            soma = soma + item.getProduto().getPreco() * item.getQuantidade();
        }
        this.total = soma;
    }
    public void decrementaTotal() {
        double soma = 0;
        for (ItemPedido item : this.listaItens) {
            soma =+ item.getProduto().getPreco() * item.getQuantidade();
        }
        this.total = soma;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemPedido> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Produto> itens) {
        this.listaItens = listaItens;
    }

    public void adicionarItem(ItemPedido itemPedido) {
        if (this.listaItens == null) {
            this.listaItens = new ArrayList();
            listaItens.add(itemPedido);
        } else {
            boolean produtoExistente = false;
            for (ItemPedido item : this.listaItens) {
                if (item.getProduto().getIdProduto() == itemPedido.getProduto().getIdProduto()) {
                    int quantidadeSomada = item.getQuantidade() + itemPedido.getQuantidade();
                    item.setQuantidade(quantidadeSomada);
                    produtoExistente = true;
                    break;
                }
            }
            if (!produtoExistente) {
                listaItens.add(itemPedido);

            }
        }
        this.incrementaTotal();
    }

    public void removerItem(int id) {
        boolean produtoExistente = false;
        for (ItemPedido item : this.listaItens) {
            if (item.getProduto().getIdProduto() == id) {
                listaItens.remove(item);
                this.decrementaTotal();
                break;
            }
        }
    }

    public void aumentaQuantidade(int id) {
        for (ItemPedido item : this.listaItens) {
            if (item.getProduto().getIdProduto() == id) {
                item.setQuantidade(item.getQuantidade() + 1);
                break;
            }
        }
        this.incrementaTotal();
    }

    public void diminuiQuantidade(int id) {
        for (ItemPedido item : this.listaItens) {
            if (item.getProduto().getIdProduto() == id) {
                if (item.getQuantidade() > 1) {
                    item.setQuantidade(item.getQuantidade() - 1);
                }
                break;
            }
        }
        this.incrementaTotal();
    }

    public int getNumeroItens() {
        if (this.listaItens == null) {
            return 0;
        } else {

            int soma = 0;
            for (ItemPedido item : this.listaItens) {
                soma = soma + item.getQuantidade();
            }
            return soma;
        }
    }

}
