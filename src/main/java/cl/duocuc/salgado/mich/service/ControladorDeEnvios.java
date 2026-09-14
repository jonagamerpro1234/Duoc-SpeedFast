package cl.duocuc.salgado.mich.service;

import cl.duocuc.salgado.mich.interfaces.Cancelable;
import cl.duocuc.salgado.mich.interfaces.Despachable;
import cl.duocuc.salgado.mich.interfaces.Rastreable;
import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.enums.EstadoPedido;

import java.util.ArrayList;


//Dividir responsabilidades entre pedidos  junto con el runnable

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
     * Cancela un pedido.
     * @param pedido pedido que será cancelado
     */
    @Override
    public void cancelar(Pedido pedido) {
        if (pedido != null) {
            //verificar que el pedido se encuentre cancelado en tal caso retorna y no cancelar nuevamente el pedido
            if(pedido.getEstado().equals(EstadoPedido.CANCELADO)){
                System.out.println("El pedido ya se encuentra cancelado");
                return;
            }

            pedido.setEstado(EstadoPedido.CANCELADO);

            System.out.println("Cancelando pedido " + pedido.getTipoPedido().getNombre() + " #" + pedido.getId() + "...");
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

            if (pedido.getEstado().equals(EstadoPedido.CANCELADO)) {
                System.out.println("El pedido #" + pedido.getId() + " no puede ser despachado porque está cancelado.");
                return;
            }

            historial.add(pedido);
            System.out.println("Pedido #" + pedido.getId() + " despachado correctamente.");
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
            System.out.println("- Pedido" + pedido.getTipoPedido().getNombre() + " #" + pedido.getId() + " - entregado por " + pedido.getRepartidorAsignado());
        }
    }

}
