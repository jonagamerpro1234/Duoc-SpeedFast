package cl.duocuc.salgado.mich.model;

import cl.duocuc.salgado.mich.service.ZonaDeCarga;
import cl.duocuc.salgado.mich.model.enums.EstadoPedido;

import java.util.Random;

/**
 * Representa a un repartidor encargado de entregar pedidos.
 * Los pedidos son retirados desde una zona de carga compartida.
 */
public class Repartidor implements Runnable {

    private String nombre;
    private final ZonaDeCarga zonaDeCarga;

    /**
     * Crea un nuevo repartidor.
     *
     * @param nombre nombre del repartidor
     * @param zonaDeCarga zona de carga compartida
     */
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
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
     * Ejecuta el proceso de entrega.
     * Cada repartidor retira pedidos desde la zona de carga
     * y los procesa de forma secuencial.
     */
    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                return;
            }

            pedido.setEstado(EstadoPedido.EN_REPARTO);
            pedido.setRepartidorAsignado(nombre);

            System.out.println(
                    "[Repartidor: " + nombre + "] "
                            + "Pedido #" + pedido.getId()
                            + " en reparto."
            );

            try {

                int tiempoEntrega = random.nextInt(2000) + 1000;
                Thread.sleep(tiempoEntrega);
                pedido.setEstado(EstadoPedido.ENTREGADO);

                System.out.println(
                        "[Repartidor: " + nombre + "] "
                                + "Pedido #" + pedido.getId()
                                + " entregado."
                );
                System.out.println();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                System.out.println(
                        "[Repartidor: " + nombre
                                + "] La entrega fue interrumpida."
                );

                return;
            }
        }
    }
}