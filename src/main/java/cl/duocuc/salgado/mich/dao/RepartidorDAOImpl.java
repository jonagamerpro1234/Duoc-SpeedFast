package cl.duocuc.salgado.mich.dao;

import cl.duocuc.salgado.mich.model.Repartidor;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAOImpl implements RepartidorDAO {

    public boolean create() {

        String sql = """
                CREATE TABLE IF NOT EXISTS repartidor (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nombre VARCHAR(100) NOT NULL
                )
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.execute();

            System.out.println("[MYSQL]: Se ha creado correctamente la tabla 'repartidor'.");
            return true;

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al crear tabla 'repartidor'.");
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
            ps.setString(1, "repartidor");

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    boolean existe = rs.getInt(1) > 0;

                    if (!existe) {

                        System.out.println("[MYSQL]: La tabla 'repartidor' no existe.");
                        return create();
                    }

                    System.out.println("[MYSQL]: La tabla 'repartidor' existe.");
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al comprobar la tabla 'repartidor'.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void guardar(@NotNull Repartidor repartidor) {

        String sql = """
                INSERT INTO repartidor (nombre)
                VALUES (?)
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            ps.executeUpdate();

            System.out.println("[MYSQL]: Repartidor guardado correctamente.");
        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al guardar el repartidor.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Repartidor> listarTodos(ZonaDeCarga zonaDeCarga) {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = """
                SELECT id, nombre
                FROM repartidor
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String nombre = rs.getString("nombre");

                Repartidor repartidor =
                        new Repartidor(nombre, zonaDeCarga);

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al listar los repartidores.");
            System.out.println(e.getMessage());
        }
        return repartidores;
    }

    @Override
    public void actualizar(@NotNull Repartidor repartidor) {

        String sql = """
                UPDATE repartidor
                SET nombre = ?
                WHERE nombre = ?
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            String nombreActual = repartidor.getNombre();

            ps.setString(1, repartidor.getNombre());
            ps.setString(2, nombreActual);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("[MYSQL]: Repartidor actualizado correctamente.");
            } else {
                System.out.println("[MYSQL]: No se encontró el repartidor.");
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al actualizar el repartidor.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = """
                DELETE FROM repartidor
                WHERE id = ?
                """;

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("[MYSQL]: Repartidor eliminado correctamente.");
            } else {
                System.out.println("[MYSQL]: No se encontró el repartidor con ID " + id + ".");
            }

        } catch (SQLException e) {
            System.out.println("[MYSQL]: Error al eliminar el repartidor.");
            System.out.println(e.getMessage());
        }
    }

}