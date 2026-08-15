package com.duocuc.salgado.mich.model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(String idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Express");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Express]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
    }

    public void asignarRepartidor(String repartidor) {
        System.out.println("[Pedido Express]");
        System.out.println("Asignando pedido al repartidor: " + repartidor);
        System.out.println("→ Verificando disponibilidad inmediata...");
        System.out.println("→ Repartidor disponible. Pedido asignado.");
    }
}