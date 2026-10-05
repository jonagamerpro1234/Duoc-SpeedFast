package cl.duocuc.salgado.mich.model;

import cl.duocuc.salgado.mich.model.enums.EstadoPedido;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;

import java.util.Random;

public class Repartidor implements Runnable {

    private int id;
    private String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public Repartidor(int id, String nombre, ZonaDeCarga zonaDeCarga) {
        this.id = id;
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
            System.out.println("[Repartidor: " + nombre + "] " + "Pedido #" + pedido.getId() + " en reparto.");

            try {
                int tiempoEntrega = random.nextInt(2000) + 1000;
                Thread.sleep(tiempoEntrega);
                pedido.setEstado(EstadoPedido.ENTREGADO);

                System.out.println("[Repartidor: " + nombre + "] " + "Pedido #" + pedido.getId() + " entregado.");
                System.out.println();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Repartidor: " + nombre + "] La entrega fue interrumpida.");
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Repartidor #" + id + " - " + nombre;
    }

}