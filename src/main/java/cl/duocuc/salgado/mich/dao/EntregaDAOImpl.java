package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Entrega;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAOImpl implements EntregaDAO {

    public boolean create() {

        String sql = """
                CREATE TABLE IF NOT EXISTS entrega (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    id_pedido INT NOT NULL,
                    id_repartidor INT NOT NULL,
                    fecha DATE NOT NULL,
                    hora TIME NOT NULL,
                    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
                    FOREIGN KEY (id_repartidor) REFERENCES repartidor(id)
                )
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();
            System.out.println("[MYSQL]: Se ha creado correctamente la tabla 'entrega'.");
            return true;
        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al crear tabla 'entrega'.");
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

            ps.setString(1, "entrega");
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    boolean existe = rs.getInt(1) > 0;

                    if (!existe) {
                        System.out.println("[MYSQL]: La tabla 'entrega' no existe.");
                        return create();
                    }

                    System.out.println("[MYSQL]: La tabla 'entrega' existe.");
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al comprobar la tabla 'entrega'.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void guardar(@NotNull Entrega entrega) {

        String sql = """
                INSERT INTO entrega
                (id_pedido, id_repartidor, fecha, hora)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));
            ps.executeUpdate();

            System.out.println("[MYSQL]: Entrega guardada correctamente.");
        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al guardar la entrega.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Entrega> listarTodos() {

        List<Entrega> entregas = new ArrayList<>();

        String sql = """
                SELECT id, id_pedido, id_repartidor, fecha, hora
                FROM entrega
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                int idPedido = rs.getInt("id_pedido");
                int idRepartidor = rs.getInt("id_repartidor");

                Date fecha = rs.getDate("fecha");
                Time hora = rs.getTime("hora");

                Entrega entrega = new Entrega(
                        id,
                        idPedido,
                        idRepartidor,
                        fecha.toLocalDate(),
                        hora.toLocalTime()
                );

                entregas.add(entrega);
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al listar las entregas.");
            System.out.println(e.getMessage());
        }

        return entregas;
    }

    @Override
    public void actualizar(@NotNull Entrega entrega) {

        String sql = """
                UPDATE entrega
                SET id_pedido = ?,
                    id_repartidor = ?,
                    fecha = ?,
                    hora = ?
                WHERE id = ?
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3,Date.valueOf(entrega.getFecha()));
            ps.setTime(4,Time.valueOf(entrega.getHora()));
            ps.setInt(5, entrega.getId());
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(
                        "[MYSQL]: Entrega #"
                                + entrega.getId()
                                + " actualizada correctamente.");

            } else {
                System.out.println(
                        "[MYSQL]: No se encontró la entrega #"
                                + entrega.getId()
                                + "."
                );
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al actualizar la entrega.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = """
                DELETE FROM entrega
                WHERE id = ?
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("[MYSQL]: Entrega #"+ id + " eliminada correctamente.");
            } else {
                System.out.println("[MYSQL]: No se encontró la entrega #" + id + ".");
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al eliminar la entrega.");
            System.out.println(e.getMessage());
        }
    }
}