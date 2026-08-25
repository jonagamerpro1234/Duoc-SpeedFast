package com.duocuc.salgado.mich.model;

/**
 * Representa un pedido genérico dentro del sistema SpeedFast.
 * Contiene la información básica de un pedido y define métodos
 * comunes para las clases derivadas.
 */
public abstract class Pedido {

    private final String idPedido;
    private final String direccionEntrega;
    private final double distanciaKm;
    private final String tipoPedido;
    private boolean cancelado;

    /**
     * Crea un nuevo pedido.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     * @param tipoPedido tipo de pedido
     */
    public Pedido(String idPedido, String direccionEntrega, double distanciaKm, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
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
     * Obtiene la dirección de entrega.
     * @return dirección de entrega
     */
    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    /**
     * Obtiene la distancia hasta el lugar de entrega.
     * @return distancia en kilómetros
     */
    public double getDistanciaKm() {
        return distanciaKm;
    }

    /**
     * Obtiene el tipo de pedido.
     * @return tipo de pedido
     */
    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    /**
     * Muestra un resumen con los datos básicos del pedido.
     */
    public void mostrarResumen() {
        System.out.println("Pedido" + tipoPedido + " #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    /**
     * Calcula el tiempo estimado de entrega.
     * Cada tipo de pedido implementa su propia lógica.
     *
     * @return tiempo estimado de entrega en minutos
     */
    public abstract int calcularTiempoEntrega();

    /**
     * Asigna un repartidor al pedido utilizando una lógica genérica.
     * Este método puede ser sobrescrito por las clases derivadas
     * para implementar comportamientos específicos.
     */
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para el pedido...");
    }
}