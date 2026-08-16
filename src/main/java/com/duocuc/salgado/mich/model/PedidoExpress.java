package com.duocuc.salgado.mich.model;

/**
 * Representa un pedido Express dentro del sistema SpeedFast.
 * Este tipo de pedido requiere asignar el repartidor más cercano
 * que tenga disponibilidad inmediata.
 */
public class PedidoExpress extends Pedido {

    /**
     * Crea un nuevo pedido Express.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     */
    public PedidoExpress(String idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Express");
    }

    /**
     * Sobrescribe el método de asignación de repartidor para
     * seleccionar al repartidor más cercano con disponibilidad inmediata.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Express]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
    }

    /**
     * Asigna un repartidor específico al pedido Express
     * considerando su disponibilidad inmediata.
     *
     * @param repartidor nombre del repartidor asignado
     */
    public void asignarRepartidor(String repartidor) {
        System.out.println("[Pedido Express]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
        System.out.println("→ Pedido asignado a " + repartidor);
    }
}