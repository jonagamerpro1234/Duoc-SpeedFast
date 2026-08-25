package com.duocuc.salgado.mich.service;

import com.duocuc.salgado.mich.interfaces.Cancelable;
import com.duocuc.salgado.mich.interfaces.Despachable;
import com.duocuc.salgado.mich.interfaces.Rastreable;
import com.duocuc.salgado.mich.model.Pedido;

import java.util.ArrayList;

public class ControladorDeEnvios implements Cancelable, Despachable, Rastreable {

    private final ArrayList<Pedido> historial;


    public ControladorDeEnvios() {
        historial = new ArrayList<>();
    }

    @Override
    public void cancel(Pedido pedido) {

    }

    @Override
    public void despachar(Pedido pedido) {

    }

    @Override
    public void verHistorial() {

        for (Pedido pedido : historial) {
            System.out.println(pedido);
        }

    }
}
