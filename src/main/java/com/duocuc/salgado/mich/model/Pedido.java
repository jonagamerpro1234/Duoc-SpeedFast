package com.duocuc.salgado.mich.model;

public class Pedido {

    private final String idPedido;
    private final String direccionEntrega;
    private final String tipoPedido;

    public Pedido(String idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void asignarRepartidor(){
        System.out.println("Asignando repartidor para el pedido...");
    }
}
