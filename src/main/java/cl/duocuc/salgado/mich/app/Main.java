package cl.duocuc.salgado.mich.app;

import cl.duocuc.salgado.mich.dao.ConexionDB;
import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.PedidoComida;
import cl.duocuc.salgado.mich.model.PedidoEncomienda;
import cl.duocuc.salgado.mich.model.PedidoExpress;
import cl.duocuc.salgado.mich.model.Repartidor;
import cl.duocuc.salgado.mich.service.ControladorDeEnvios;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;
import cl.duocuc.salgado.mich.ui.Menu;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal encargada de ejecutar la simulación concurrente
 * de entregas del sistema SpeedFast.
 */
public class Main {

    private static Connection conexion;

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        //Inicializar Conexion con DB
        initDB();

        //Inicializar ventana de app
        new Menu().init();

        // Crear zona de carga compartida
        ZonaDeCarga zonaDeCarga = getZonaDeCarga();

        // Crear controlador de envíos
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // Crear pedido que será cancelado
        Pedido pedidoCancelado = new PedidoComida(107, "Av. Vicuña Mackenna 500", 6.0d);

        // Agregar pedido a la zona de carga
        zonaDeCarga.agregarPedido(pedidoCancelado);

        // Cancelar pedido antes de iniciar los repartidores
        controlador.cancelar(pedidoCancelado);
        System.out.println();

        // Crear repartidores utilizando la misma zona de carga
        Repartidor luis = new Repartidor("Luis", zonaDeCarga);
        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor daniela = new Repartidor("Daniela", zonaDeCarga);

        // Crear ExecutorService con tres hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("=== INICIANDO SIMULACIÓN DE ENTREGAS ===");
        System.out.println();

        // Ejecutar los tres repartidores de forma concurrente
        executor.submit(luis);
        executor.submit(camila);
        executor.submit(daniela);

        // No se aceptan nuevas tareas
        executor.shutdown();

        try {

            if(!executor.awaitTermination(1, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }

            // Esperar a que todos los repartidores terminen
            //executor.awaitTermination(1, TimeUnit.MINUTES);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La simulación fue interrumpida.");
        }

        System.out.println();
        System.out.println("Todos los pedidos han sido entregados correctamente");
    }

    /**
     * Crea y carga la zona de carga con los pedidos disponibles.
     * @return zona de carga con los pedidos registrados
     */
    private static @NotNull ZonaDeCarga getZonaDeCarga() {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Crear pedidos
        Pedido comida1 = new PedidoComida(101, "Av. Las Condes 123", 10.2d);
        Pedido encomienda1 = new PedidoEncomienda(102, "Av. Santa Rosa 567", 7.0d);
        Pedido express1 = new PedidoExpress(103, "Av. Los Leones 046", 5.6d);
        Pedido comida2 = new PedidoComida(104, "Av. Providencia 890", 3.5d);
        Pedido encomienda2 = new PedidoEncomienda(105, "Av. Apoquindo 1200", 8.3d);
        Pedido express2 = new PedidoExpress(106, "Av. Independencia 450", 4.2d);

        // Agregar pedidos a la zona de carga
        zonaDeCarga.agregarPedido(comida1);
        zonaDeCarga.agregarPedido(encomienda1);
        zonaDeCarga.agregarPedido(express1);
        zonaDeCarga.agregarPedido(comida2);
        zonaDeCarga.agregarPedido(encomienda2);
        zonaDeCarga.agregarPedido(express2);

        return zonaDeCarga;
    }

    private static void initDB() {
        try {
            conexion = ConexionDB.conectar();
            System.out.println("[MySQL] Conexión establecida correctamente.");
        } catch (SQLException e) {
            conexion = null;
            System.out.println("[MySQL] Error al conectar.");
            System.out.println();
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
}