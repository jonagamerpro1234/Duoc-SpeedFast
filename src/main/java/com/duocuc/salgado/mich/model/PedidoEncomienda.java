package com.duocuc.salgado.mich.model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(String idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Encomienda]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
    }

    public void asignarRepartidor(String repartidor) {
        System.out.println("[Pedido Encomienda]");
        System.out.println("Asignando pedido al repartidor: " + repartidor);
        System.out.println("→ Verificando disponibilidad inmediata...");
        System.out.println("→ Repartidor disponible. Pedido asignado.");
    }
}
