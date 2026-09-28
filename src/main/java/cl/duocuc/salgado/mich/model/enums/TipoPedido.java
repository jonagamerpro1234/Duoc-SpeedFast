package cl.duocuc.salgado.mich.model.enums;

/**
 * Representa los tipos de pedidos disponibles en SpeedFast.
 */
public enum TipoPedido {

    COMIDA("Comida"),
    ENCOMIENDA("Encomienda"),
    EXPRESS("Express");

    /**
     * Nombre descriptivo del tipo de pedido.
     */
    private final String nombre;

    /**
     * Crea un tipo de pedido.
     * @param nombre nombre descriptivo del tipo de pedido
     */
    TipoPedido(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre descriptivo del tipo de pedido.
     * @return nombre del tipo de pedido
     */
    public String getNombre() {
        return nombre;
    }
}