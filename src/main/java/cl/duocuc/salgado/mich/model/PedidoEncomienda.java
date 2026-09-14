package cl.duocuc.salgado.mich.model;

import cl.duocuc.salgado.mich.model.enums.TipoPedido;

/**
 * Representa un pedido de encomienda dentro del sistema SpeedFast.
 */
public class PedidoEncomienda extends Pedido {

    /**
     * Crea un nuevo pedido de encomienda.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm, TipoPedido.ENCOMIENDA);
    }

    /**
     * Calcula el tiempo estimado de entrega del pedido.
     * Se consideran 20 minutos base más 1.5 minutos por cada kilómetro.
     *
     * @return tiempo estimado de entrega en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(20 + (1.5 * getDistanciaKm()));
    }
}