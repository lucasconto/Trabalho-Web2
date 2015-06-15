package br.com.magazine.entidade;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    double total;
    List<ItemPedido> listaItens;

    public void incrementaTotal() {
        double soma = 0;
        for(ItemPedido item : this.listaItens){
            soma = soma + item.getProduto().getPreco() * item.getQuantidade();
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
        if(this.listaItens == null){
            this.listaItens = new ArrayList();
            listaItens.add(itemPedido);
        }else{
            boolean produtoExistente = false;
            for(ItemPedido item : this.listaItens){
                if(item.getProduto().getIdProduto() == itemPedido.getProduto().getIdProduto()){
                    int quantidadeSomada = item.getQuantidade() + itemPedido.getQuantidade();
                    item.setQuantidade(quantidadeSomada);
                    produtoExistente = true;
                    break;
                }
            }
            if(!produtoExistente){
                listaItens.add(itemPedido);
                
            }
        }
        this.incrementaTotal();
    }
    
    public int getNumeroItens() {
        if(this.listaItens == null){
            return 0;
        }else{
            
        int soma = 0;
        for(ItemPedido item : this.listaItens){
            soma = soma + item.getQuantidade();
        }
        return soma;
        }
    }

}
