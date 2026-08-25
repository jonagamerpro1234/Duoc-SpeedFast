package com.duocuc.salgado.mich.app;

import com.duocuc.salgado.mich.model.Pedido;
import com.duocuc.salgado.mich.model.PedidoComida;
import com.duocuc.salgado.mich.model.PedidoEncomienda;
import com.duocuc.salgado.mich.model.PedidoExpress;
import com.duocuc.salgado.mich.service.ControladorDeEnvios;

/**
 * Clase principal encargada de ejecutar y probar el sistema SpeedFast.
 * Demuestra el uso de clases abstractas, herencia, sobrescritura,
 * sobrecarga, polimorfismo e interfaces.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        PedidoComida comida = new PedidoComida("101", "Av. Las Condes 123", 10.2d);
        PedidoEncomienda encomienda = new PedidoEncomienda("102", "Av. Santa Rosa 567", 7.0d);
        PedidoExpress express = new PedidoExpress("103", "Av. Los Leones 046", 5.6d);

        Pedido[] pedidos = {comida, encomienda, express};

        System.out.println();
        // Mostrar información y tiempo estimado
        for (Pedido pedido : pedidos) {
            pedido.mostrarResumen();
            System.out.println("Tiempo estimado: " + pedido.calcularTiempoEntrega() + " minutos");
            System.out.println();
        }

        // Asignación automática
        comida.asignarRepartidor();
        System.out.println();

        // Asignación manual
        comida.asignarRepartidor("Luis Díaz");
        System.out.println();
        encomienda.asignarRepartidor("Daniela Tapia");
        System.out.println();

        // Despachar pedidos
        controlador.despachar(comida);
        System.out.println();
        controlador.despachar(encomienda);
        System.out.println();

        // Cancelar pedido
        controlador.cancelar(express);
        System.out.println();

        // Mostrar historial
        controlador.verHistorial();
    }
}