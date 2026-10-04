package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Repartidor;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;

import java.util.List;

public interface RepartidorDAO {

    void guardar(Repartidor repartidor);

    List<Repartidor> listarTodos(ZonaDeCarga zonaDeCarga);

    void actualizar(Repartidor repartidor);

    void eliminar(int id);
}