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
                new PedidoComida("001", "Av. Las Condes 123", 10.2d),
                new PedidoEncomienda("002", "Av. Los Leones 046", 5.6d),
                new PedidoExpress("003", "Av. Santa Rosa 1623", 7.9d)
        };

        for (Pedido pedido : pedidos) {
            //pedido.asignarRepartidor();
            pedido.mostrarResumen();
            System.out.println();
        }


        //Semana 1
//        PedidoComida comida = (PedidoComida) pedidos[0];
//        PedidoEncomienda encomienda = (PedidoEncomienda) pedidos[1];
//        PedidoExpress express = (PedidoExpress) pedidos[2];
//
//        comida.asignarRepartidor("Juan Pérez");
//        System.out.println();
//        comida.mostrarResumen();
//        System.out.println();
//
//        encomienda.asignarRepartidor("Camila Soto");
//        System.out.println();
//        encomienda.mostrarResumen();
//        System.out.println();
//
//        express.asignarRepartidor("Luis Díaz");
//        System.out.println();
//        express.mostrarResumen();
    }
}