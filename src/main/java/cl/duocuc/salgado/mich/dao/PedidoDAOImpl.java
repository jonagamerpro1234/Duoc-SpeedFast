package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.PedidoComida;
import cl.duocuc.salgado.mich.model.PedidoEncomienda;
import cl.duocuc.salgado.mich.model.PedidoExpress;
import cl.duocuc.salgado.mich.model.enums.EstadoPedido;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "pedido");

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    boolean existe = rs.getInt(1) > 0;

                    if (!existe) {

                        System.out.println("[MYSQL]: La tabla 'pedido' no existe.");
                        return create();
                    }

                    System.out.println("[MYSQL]: La tabla 'pedido' existe.");
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al comprobar la tabla 'pedido'.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void guardar(@NotNull Pedido pedido) {

        String sql = """
                INSERT INTO pedido
                (id, direccion, tipo, estado)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getId());
            ps.setString(2, pedido.getDireccionEntrega());
            ps.setString(3, pedido.getTipoPedido().name());
            ps.setString(4, pedido.getEstado().name());

            ps.executeUpdate();

            System.out.println("[MYSQL]: Se ha guardado correctamente el pedido.");

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al guardar el pedido.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
                SELECT id, direccion, tipo, estado
                FROM pedido
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                String estado = rs.getString("estado");

                Pedido pedido;
                switch (tipo) {
                    case "COMIDA" ->
                            pedido = new PedidoComida(
                                    id,
                                    direccion,
                                    0
                            );
                    case "ENCOMIENDA" ->
                            pedido = new PedidoEncomienda(
                                    id,
                                    direccion,
                                    0
                            );
                    case "EXPRESS" ->
                            pedido = new PedidoExpress(
                                    id,
                                    direccion,
                                    0
                            );

                    default -> {
                        System.out.println("[MYSQL]: Tipo de pedido desconocido: " + tipo);
                        continue;
                    }
                }

                pedido.setEstado(EstadoPedido.valueOf(estado));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al listar los pedidos.");
            System.out.println(e.getMessage());
        }

        return pedidos;
    }

    @Override
    public void actualizar(@NotNull Pedido pedido) {

        String sql = """
                UPDATE pedido
                SET direccion = ?, tipo = ?, estado = ?
                WHERE id = ?
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipoPedido().name());
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("[MYSQL]: El Pedido #" + pedido.getId() + " se ha actualizado correctamente.");
            } else {
                System.out.println("[MYSQL]: No se encontró el pedido #" + pedido.getId() + ".");
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al actualizar el pedido.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = """
                DELETE FROM pedido
                WHERE id = ?
                """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("[MYSQL]: Se ha eliminado el pedido #" + id + " correctamente.");
            } else {
                System.out.println("[MYSQL]: No se encontró el pedido #" + id + ".");
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al eliminar el pedido.");
            System.out.println(e.getMessage());
        }
    }
}