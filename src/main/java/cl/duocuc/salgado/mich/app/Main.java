package cl.duocuc.salgado.mich.app;

import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.PedidoComida;
import cl.duocuc.salgado.mich.model.PedidoEncomienda;
import cl.duocuc.salgado.mich.model.PedidoExpress;
import cl.duocuc.salgado.mich.model.Repartidor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase principal encargada de ejecutar la simulación concurrente
 * de entregas del sistema SpeedFast.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        // Pedidos del repartidor Luis
        Pedido comida1 = new PedidoComida("101", "Av. Las Condes 123", 10.2d);
        Pedido encomienda1 = new PedidoEncomienda("102", "Av. Santa Rosa 567", 7.0d);

        // Pedidos del repartidor Camila
        Pedido express1 = new PedidoExpress("103", "Av. Los Leones 046", 5.6d);
        Pedido comida2 = new PedidoComida("104", "Av. Providencia 890", 3.5d);

        // Pedidos del repartidor Daniela
        Pedido encomienda2 = new PedidoEncomienda("105", "Av. Apoquindo 1200", 8.3d);
        Pedido express2 = new PedidoExpress("106", "Av. Independencia 450", 4.2d);

        // Listas de pedidos para cada repartidor
        List<Pedido> pedidosLuis = new ArrayList<>();
        pedidosLuis.add(comida1);
        pedidosLuis.add(encomienda1);

        List<Pedido> pedidosCamila = new ArrayList<>();
        pedidosCamila.add(express1);
        pedidosCamila.add(comida2);

        List<Pedido> pedidosDaniela = new ArrayList<>();
        pedidosDaniela.add(encomienda2);
        pedidosDaniela.add(express2);

        // Crear repartidores
        Repartidor luis = new Repartidor(   "Luis", pedidosLuis);
        Repartidor camila = new Repartidor("Camila", pedidosCamila);
        Repartidor daniela = new Repartidor("Daniela", pedidosDaniela);

        // Crear ExecutorService con tres hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("=== INICIANDO SIMULACIÓN DE ENTREGAS ===");
        System.out.println();

        // Ejecutar repartidores de forma concurrente
        executor.submit(luis);
        executor.submit(camila);
        executor.submit(daniela);

        // No se aceptan nuevas tareas
        executor.shutdown();

        try {
            executor.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}