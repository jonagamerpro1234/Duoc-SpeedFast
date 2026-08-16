package com.duocuc.salgado.mich.model;

/**
 * Representa un pedido genérico dentro del sistema SpeedFast.
 * Contiene la información básica de un pedido y define el método
 * para asignar un repartidor.
 */
public class Pedido {

    private final String idPedido;
    private final String direccionEntrega;
    private final String tipoPedido;

    /**
     * Crea un nuevo pedido.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param tipoPedido tipo de pedido
     */
    public Pedido(String idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    /**
     * Obtiene el identificador del pedido.
     * @return identificador del pedido
     */
    public String getIdPedido() {
        return idPedido;
    }

    /**
     * Obtiene la dirección de entrega del pedido.
     *
     * @return dirección de entrega
     */
    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    /**
     * Obtiene el tipo de pedido.
     * @return tipo de pedido
     */
    public String getTipoPedido() {
        return tipoPedido;
    }

    /**
     * Asigna un repartidor al pedido utilizando una lógica genérica.
     * Este método puede ser sobrescrito por las clases derivadas
     * para implementar comportamientos específicos.
     */
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para el pedido...");
    }
}