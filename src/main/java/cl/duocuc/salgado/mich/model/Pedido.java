package cl.duocuc.salgado.mich.model;

import cl.duocuc.salgado.mich.model.enums.EstadoPedido;
import cl.duocuc.salgado.mich.model.enums.PrioridadPedido;
import cl.duocuc.salgado.mich.model.enums.TipoPedido;

/**
 * Representa un pedido genérico dentro del sistema SpeedFast.
 * Contiene la información básica de un pedido y define métodos
 * comunes para las clases derivadas.
 */
public abstract class Pedido {

    // Atributos del pedido
    private final int id;
    private final String direccionEntrega;
    private final double distanciaKm;
    private String repartidorAsignado;
    private final TipoPedido tipoPedido;
    private EstadoPedido estado;
    private PrioridadPedido prioridad;

    /**
     * Crea un nuevo pedido.
     *
     * @param id identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     * @param tipoPedido tipo de pedido
     */
    public Pedido(int id, String direccionEntrega, double distanciaKm, TipoPedido tipoPedido) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.tipoPedido = tipoPedido;
        this.estado = EstadoPedido.PENDIENTE;
        this.prioridad = PrioridadPedido.MEDIA;
    }

    /**
     * Obtiene el identificador del pedido.
     * @return identificador del pedido
     */
    public int getId() {
        return id;
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
    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    /**
     * Obtiene el estado actual del pedido.
     * @return estado del pedido
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Actualiza el estado del pedido.
     * @param estado nuevo estado del pedido
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la prioridad del pedido.
     * @return prioridad del pedido
     */
    public PrioridadPedido getPrioridad() {
        return prioridad;
    }

    /**
     * Modifica la prioridad del pedido.
     * @param prioridad nueva prioridad del pedido
     */
    public void setPrioridad(PrioridadPedido prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Indica si el pedido se encuentra disponible para ser retirado.
     * @return true si el pedido está pendiente
     */
    public boolean estaDisponible() {
        return estado == EstadoPedido.PENDIENTE;
    }

    /**
     * Obtiene el nombre del repartidor asignado.
     * @return nombre del repartidor asignado
     */
    public String getRepartidorAsignado() {
        return repartidorAsignado;
    }

    /**
     * Asigna un repartidor al pedido.
     * @param repartidorAsignado nombre del repartidor asignado
     */
    public void setRepartidorAsignado(String repartidorAsignado) {
        this.repartidorAsignado = repartidorAsignado;
    }

    /**
     * Muestra un resumen con los datos básicos del pedido.
     */
    public void mostrarResumen() {
        System.out.println("[Pedido " + tipoPedido.getNombre() + "]");
        System.out.println("Pedido #" + id);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Estado: " + estado);
        System.out.println("Prioridad: " + prioridad.getNombre());
    }

    /**
     * Calcula el tiempo estimado de entrega.
     * Cada tipo de pedido implementa su propia lógica.
     * @return tiempo estimado de entrega en minutos
     */
    public abstract int calcularTiempoEntrega();

    /**
     * Asigna un repartidor al pedido utilizando una lógica genérica.
     */
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para el pedido...");
    }
}