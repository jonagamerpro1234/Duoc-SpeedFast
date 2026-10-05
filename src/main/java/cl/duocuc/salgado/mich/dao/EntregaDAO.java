package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Entrega;

import java.util.List;

public interface EntregaDAO {

    void guardar(Entrega entrega);

    List<Entrega> listarTodos();

    void actualizar(Entrega entrega);

    void eliminar(int id);
}