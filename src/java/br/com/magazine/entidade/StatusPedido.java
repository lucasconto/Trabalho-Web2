/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.magazine.entidade;

/**
 *
 * @author Evandro
 */
public class StatusPedido {
    private int idStatusPedido;
    private String descricao;

    /**
     * @return the idStatusPedido
     */
    public int getIdStatusPedido() {
        return idStatusPedido;
    }

    /**
     * @param idStatusPedido the idStatusPedido to set
     */
    public void setIdStatusPedido(int idStatusPedido) {
        this.idStatusPedido = idStatusPedido;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
