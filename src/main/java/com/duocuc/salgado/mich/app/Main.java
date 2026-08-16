package com.duocuc.salgado.mich.app;

import com.duocuc.salgado.mich.model.Pedido;
import com.duocuc.salgado.mich.model.PedidoComida;
import com.duocuc.salgado.mich.model.PedidoEncomienda;
import com.duocuc.salgado.mich.model.PedidoExpress;

/**
 * Clase principal encargada de ejecutar y probar el sistema SpeedFast.
 * Demuestra el uso de herencia, sobrescritura, sobrecarga y polimorfismo.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        Pedido[] pedidos = {
                new PedidoComida("001", "Av. Las Condes 123"),
                new PedidoEncomienda("002", "Av. Los Leones 046"),
                new PedidoExpress("003", "Av. Santa Rosa 1623")
        };

        for (Pedido pedido : pedidos) {
            pedido.asignarRepartidor();
            System.out.println();
        }

        PedidoComida comida = (PedidoComida) pedidos[0];
        PedidoEncomienda encomienda = (PedidoEncomienda) pedidos[1];
        PedidoExpress express = (PedidoExpress) pedidos[2];

        comida.asignarRepartidor("Juan Pérez");
        System.out.println();

        encomienda.asignarRepartidor("Camila Soto");
        System.out.println();

        express.asignarRepartidor("Luis Díaz");
    }
}