package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Pedido;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO {

    public void create() {

        String sql = """
                CREATE TABLE IF NOT EXISTS pedido (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    direccion VARCHAR(150) NOT NULL,
                    tipo VARCHAR(30) NOT NULL,
                    estado VARCHAR(20) NOT NULL
                )
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.execute();

            System.out.println("[MySQL] Tabla 'pedido' creada correctamente.");

        } catch (SQLException e) {
            System.out.println("[MySQL] Error al crear la tabla 'pedido'.");
            System.out.println(e.getMessage());
        }
    }

    public boolean existTable() {

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
                        create();
                    }

                    return existe;
                }
            }

        } catch (SQLException e) {
            System.out.println("[MySQL] Error al comprobar la tabla 'pedido'.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void guardar(@NotNull Pedido pedido) {

        String sql = """
                INSERT INTO pedido (id, direccion, tipo, estado)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, pedido.getId());
            statement.setString(2, pedido.getDireccionEntrega());
            statement.setString(3, pedido.getTipoPedido().name());
            statement.setString(4, pedido.getEstado().name());

            statement.executeUpdate();

            System.out.println("[MySQL] Pedido #"+ pedido.getId()+ " guardado correctamente.");

        } catch (SQLException e) {
            System.out.println("[MySQL] Error al guardar pedido.");
            System.out.println(e.getMessage());
        }
    }
}