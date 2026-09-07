package cl.duocuc.salgado.mich.model;

import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidos;

    public Repartidor(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        } else {
            System.out.println("El pedido ya existe.");
        }
    }

    @Override
    public void run() {
        Random random = new Random();

        for (Pedido pedido : pedidos) {

            System.out.println("[Repartidor: " + nombre + "] Entregando Pedido"
                    + pedido.getTipoPedido()
                    + " #" + pedido.getIdPedido() + "...");

            try {
                int tiempoEspera = random.nextInt(2000) + 1000;
                Thread.sleep(tiempoEspera);

                System.out.println("[Repartidor: " + nombre + "] Pedido #"
                        + pedido.getIdPedido() + " entregado.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("La entrega fue interrumpida.");
                return;
            }
        }
    }
}