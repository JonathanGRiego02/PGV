package pgv.hilos.wok;

public class Cocinero {

    private final String nombre;
    private String pedido_actual;

    public String getNombre() {
        return nombre;
    }

    public String getPedido_actual() {
        return pedido_actual;
    }

    public void setPedido_actual(String pedido_actual) {
        this.pedido_actual = pedido_actual;
    }

    public Cocinero (String _nombre) {
        this.nombre = _nombre;
    }

}
