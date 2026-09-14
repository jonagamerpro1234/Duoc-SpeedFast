package cl.duocuc.salgado.mich.model;

import java.util.List;
import java.util.Random;

/**
 * Representa a un repartidor encargado de entregar pedidos.
 * Cada repartidor procesa sus pedidos de forma secuencial.
 */
public class Repartidor implements Runnable {

    private String nombre;
    private final List<Pedido> pedidos;

    /**
     * Crea un nuevo repartidor.
     *
     * @param nombre nombre del repartidor
     * @param pedidos lista de pedidos asignados
     */
    public Repartidor(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    /**
     * Obtiene el nombre del repartidor.
     *
     * @return nombre del repartidor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del repartidor.
     *
     * @param nombre nuevo nombre del repartidor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los pedidos asignados al repartidor.
     *
     * @return lista de pedidos
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Agrega un pedido a la lista del repartidor.
     *
     * @param pedido pedido que será agregado
     */
    public void addPedido(Pedido pedido) {
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        } else {
            System.out.println("El pedido ya existe.");
        }
    }

    /**
     * Ejecuta el proceso de entrega de los pedidos asignados.
     * Los pedidos se entregan de forma secuencial.
     */
    @Override
    public void run() {

        Random random = new Random();
        for (Pedido pedido : pedidos) {

            System.out.println(
                    "[Repartidor: " + nombre + "] Entregando Pedido"
                            + pedido.getTipoPedido()
                            + " #" + pedido.getId() + "..."
            );

            try {

                int tiempoEntrega = random.nextInt(2000) + 1000;
                Thread.sleep(tiempoEntrega);

                System.out.println(
                        "[Repartidor: " + nombre + "] Pedido"
                                + pedido.getTipoPedido()
                                + " #" + pedido.getId()
                                + " entregado."
                );
                System.out.println();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Repartidor: " + nombre + "] La entrega fue interrumpida.");
                return;
            }
        }
    }
}