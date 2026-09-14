package cl.duocuc.salgado.mich.model;

import cl.duocuc.salgado.mich.model.enums.TipoPedido;

/**
 * Representa un pedido Express dentro del sistema SpeedFast.
 */
public class PedidoExpress extends Pedido {

    /**
     * Crea un nuevo pedido Express.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     */
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm, TipoPedido.EXPRESS);
    }

    /**
     * Calcula el tiempo estimado de entrega del pedido Express.
     * El tiempo es de 10 minutos para distancias de hasta 5 kilómetros
     * y de 15 minutos para distancias superiores a 5 kilómetros.
     *
     * @return tiempo estimado de entrega en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        if (getDistanciaKm() > 5) {
            return 15;
        }

        return 10;
    }
}