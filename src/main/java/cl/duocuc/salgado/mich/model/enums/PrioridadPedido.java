package cl.duocuc.salgado.mich.model.enums;

/**
 * Representa los niveles de prioridad de un pedido.
 */
public enum PrioridadPedido {

    ALTA("Alta"),
    MEDIA("Media"),
    BAJA("Baja");

    private final String nombre;

    /**
     * Crea una prioridad de pedido.
     *
     * @param nombre nombre descriptivo de la prioridad
     */
    PrioridadPedido(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre descriptivo de la prioridad.
     *
     * @return nombre de la prioridad
     */
    public String getNombre() {
        return nombre;
    }
}