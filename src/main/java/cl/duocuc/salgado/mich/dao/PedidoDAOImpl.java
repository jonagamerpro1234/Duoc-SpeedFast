package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    public boolean create() {

        String sql = """
                CREATE TABLE IF NOT EXISTS pedido (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    direccion VARCHAR(150) NOT NULL,
                    tipo VARCHAR(30) NOT NULL,
                    estado VARCHAR(20) NOT NULL
                )
                """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();

            System.out.println("[MYSQL]: Se ha creado correctamente la tabla 'pedido'.");
            return true;

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al crear tabla 'pedido'.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean verificarTabla() {

        String sql = """
                SELECT COUNT(*)
                FROM information_schema.tables
                WHERE table_schema = DATABASE()
                AND table_name = ?
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, "pedido");

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    boolean existe = resultSet.getInt(1) > 0;

                    if (!existe) {
                        System.out.println("[MySQL] La tabla 'pedido' no existe.");
                        return create();
                    }

                    System.out.println("[MySQL] La tabla 'pedido' existe.");
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("[MySQL] Error al comprobar la tabla 'pedido'.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void guardar(Pedido pedido) {

    }

    @Override
    public List<Pedido> listarTodos() {
        return List.of();
    }

    @Override
    public void actualizar(Pedido pedido) {

    }

    @Override
    public void eliminar(int id) {

    }
}