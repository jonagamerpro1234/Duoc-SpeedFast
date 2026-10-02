package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Pedido;

import java.util.List;

public interface EntregaDAO {

    void guardar(Pedido pedido);

    List<Pedido> listarTodos();

    void actualizar(Pedido pedido);

    void eliminar(int id);

}
