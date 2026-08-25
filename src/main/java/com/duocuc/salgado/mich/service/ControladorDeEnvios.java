package com.duocuc.salgado.mich.service;

import com.duocuc.salgado.mich.interfaces.Cancelable;
import com.duocuc.salgado.mich.interfaces.Despachable;
import com.duocuc.salgado.mich.interfaces.Rastreable;
import com.duocuc.salgado.mich.model.Pedido;

import java.util.ArrayList;

/**
 * Clase encargada de controlar y gestionar los diferentes tipos de pedidos
 */
public class ControladorDeEnvios implements Cancelable, Despachable, Rastreable {

    //Atributos
    private final ArrayList<Pedido> historial;

    //Constructor
    public ControladorDeEnvios() {
        historial = new ArrayList<>();
    }

    /**
     * Cancela un pedido y lo agrega al historial.
     * @param pedido pedido que será cancelado
     */
    @Override
    public void cancelar(Pedido pedido) {
        if (pedido != null) {
            pedido.setCancelado(true);
            System.out.println("Cancelando pedido " + pedido.getTipoPedido() + " #" + pedido.getIdPedido() + "...");
            System.out.println("→ Pedido cancelado exitosamente.");
        }
    }

    /**
     * Despacha un pedido siempre que no se encuentre cancelado.
     * @param pedido pedido que será despachado
     */
    @Override
    public void despachar(Pedido pedido) {
        if (pedido != null) {

            if (pedido.isCancelado()) {
                System.out.println("El pedido #" + pedido.getIdPedido() + " no puede ser despachado porque está cancelado.");
                return;
            }

            historial.add(pedido);
            System.out.println("Pedido despachado correctamente.");
        }
    }

    /**
     * Muestra los pedidos registrados en el historial.
     */
    @Override
    public void verHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay pedidos en el historial.");
            return;
        }

        System.out.println("Historial:");
        for (Pedido pedido : historial) {
            System.out.println("- Pedido" + pedido.getTipoPedido() + " #" + pedido.getIdPedido() + " - entregado por " + pedido.getRepartidorAsignado());
        }
    }
}
