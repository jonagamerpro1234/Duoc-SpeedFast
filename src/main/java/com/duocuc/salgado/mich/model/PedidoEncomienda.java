package com.duocuc.salgado.mich.model;

/**
 * Representa un pedido de encomienda dentro del sistema SpeedFast.
 * Este tipo de pedido requiere validar el peso y el embalaje
 * antes de realizar la asignación del repartidor.
 */
public class PedidoEncomienda extends Pedido {

    /**
     * Crea un nuevo pedido de encomienda.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     */
    public PedidoEncomienda(String idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
    }

    /**
     * Sobrescribe el método de asignación de repartidor para
     * realizar la validación del peso y embalaje.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Encomienda]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Validando peso y embalaje... OK");
    }

    /**
     * Asigna un repartidor específico al pedido de encomienda
     * realizando la validación correspondiente.
     *
     * @param repartidor nombre del repartidor asignado
     */
    public void asignarRepartidor(String repartidor) {
        System.out.println("[Pedido Encomienda]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Validando peso y embalaje... OK");
        System.out.println("→ Pedido asignado a " + repartidor);
    }
}