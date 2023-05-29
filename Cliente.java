import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private TipoCliente tipo;
    private ArrayList<Pedido> pedidos;

    // Constructor
    public Cliente(String nombre, TipoCliente tipo, Cooperativa cooperativa) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pedidos = new ArrayList<>();
        cooperativa.getClientes().add(this);
    }

    // Métodos getter y setter para los campos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
