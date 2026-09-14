package cl.duocuc.salgado.mich.service;

import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.enums.EstadoPedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la zona de carga compartida de SpeedFast.
 * <p>
 * Permite almacenar pedidos y controlar el acceso concurrente
 * de los repartidores mediante sincronización.
 */
public class ZonaDeCarga {

    private final List<Pedido> pedidos;

    /**
     * Crea una zona de carga vacía.
     */
    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
        System.out.println("[Zona de carga inicializada]");
    }

    /**
     * Agrega un pedido pendiente a la zona de carga.
     * @param pedido pedido que será agregado
     */
    public synchronized void agregarPedido(Pedido pedido) {

        if (pedido == null) {
            return;
        }

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            System.out.println(
                    "El pedido #" + pedido.getId()
                            + " no puede ser agregado porque no está pendiente."
            );
            return;
        }

        pedidos.add(pedido);

        System.out.println(
                "Pedido #" + pedido.getId()
                        + " agregado. Destino: "
                        + pedido.getDireccionEntrega()
        );
    }

    /**
     * Retira un pedido de la zona de carga.
     * @return pedido retirado o null si no existen pedidos disponibles
     */
    public synchronized Pedido retirarPedido() {

        for (int i = 0; i < pedidos.size(); i++) {

            Pedido pedido = pedidos.get(i);

            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
                return pedidos.remove(i);
            }
        }

        return null;
    }

    /**
     * Obtiene la cantidad de pedidos disponibles.
     * @return cantidad de pedidos
     */
    public synchronized int obtenerCantidadPedidos() {
        return pedidos.size();
    }
}