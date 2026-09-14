package cl.duocuc.salgado.mich.model;

import cl.duocuc.salgado.mich.model.enums.TipoPedido;

/**
 * Representa un pedido de comida dentro del sistema SpeedFast.
 */
public class PedidoComida extends Pedido {

    /**
     * Crea un nuevo pedido de comida.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     */
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm, TipoPedido.COMIDA);
    }

    /**
     * Calcula el tiempo estimado de entrega del pedido.
     * Se consideran 15 minutos base más 2 minutos por cada kilómetro.
     *
     * @return tiempo estimado de entrega en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) (15 + (2 * getDistanciaKm()));
    }
}