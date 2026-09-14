package cl.duocuc.salgado.mich.model;

/**
 * Representa un pedido de comida dentro del sistema SpeedFast.
 * Este tipo de pedido requiere verificar que el repartidor
 * disponga de una mochila térmica.
 */
public class PedidoComida extends Pedido {

    /**
     * Crea un nuevo pedido de comida.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     */
    public PedidoComida(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm,  TipoPedido.COMIDA);
    }

    /**
     * Calcular el tiempo de entrega del pedido
     * se calcula a partir de 15 min, más lo sumado de 2 min multiplicado por los Kilómetros
     * @return devuelve el tiempo calculado del los kilómetros multiplicado por 2 min
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) (15 + (2 * getDistanciaKm()));
    }

    /**
     * Sobrescribe el método de asignación de repartidor para
     * verificar la disponibilidad de una mochila térmica.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Comida]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
    }

    /**
     * Asigna un repartidor específico al pedido de comida
     * realizando la validación correspondiente.
     *
     * @param repartidor nombre del repartidor asignado
     */
    public void asignarRepartidor(String repartidor) {
        this.setRepartidorAsignado(repartidor);
        System.out.println("[Pedido Comida]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
        System.out.println("→ Pedido asignado a " + repartidor);
    }

}