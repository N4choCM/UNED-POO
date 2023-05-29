import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa a un productor de la cooperativa. Es una superclase y
 * sus hijos son PequenoProductor, GranProductor y ProductorFederado.
 */
public class Productor {
    protected ArrayList<Producto> productos;
    private Map<Producto, Double> extensionPorProducto = new HashMap<Producto, Double>();
    protected double extensionTotal;
    protected int numProductos;
    private String nombre;
    private double beneficios;
    protected final int LIMITE = 5;

    /**
     * Constructor de la clase Productor.
     * @param nombre Nombre del productor.
     * @param cooperativa Cooperativa a la que pertenece el productor.
     */
    public Productor(String nombre, Cooperativa cooperativa) {
        this.nombre = nombre;
        this.productos = new ArrayList<Producto>();
        this.extensionTotal = 0;
        this.numProductos = 0;
        this.beneficios = 0;
        cooperativa.getProductores().add(this);
    }

    // Getters y setters
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
        this.numProductos = productos.size();
    }

    public double getExtensionTotal() {
        return extensionTotal;
    }

    public void setExtensionTotal(double extensionTotal) {
        this.extensionTotal = extensionTotal;
    }

    public int getNumProductos() {
        return numProductos;
    }

    public void setNumProductos(int numProductos) {
        this.numProductos = numProductos;
    }

    public Map<Producto, Double> getExtensionPorProducto() {
        return extensionPorProducto;
    }

    public void setExtensionPorProducto(Map<Producto, Double> extensionPorProducto) {
        this.extensionPorProducto = extensionPorProducto;
    }

    public void setExtensionPorProducto(Producto producto, double extension) {
        this.extensionPorProducto.put(producto, extension);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(double beneficios) {
        this.beneficios = beneficios;
    }

    public int getLIMITE() {
        return LIMITE;
    }

    /**
     * Metodo que agrega un producto a la lista de productos del productor.
     * @param producto Producto a agregar.
     * @param extension Extension del producto a agregar.
     * @param productor Productor al que se le agrega el producto.
     */
    public void agregarProducto(Producto producto, double extension, Productor productor) {
        if(extension > producto.getExtension()) {
            System.out.println("No se puede agregar esa extension; supera la extension del producto.");
            return;
        }
        producto.agregarProductor(productor);
        producto.setExtension(producto.getExtension() - extension);
        productos.add(producto);
        setExtensionPorProducto(producto, extension);
        setExtensionTotal(extensionTotal + extension);
        setNumProductos(numProductos + 1);
    }

    /**
     * Metodo que quita un producto de la lista de productos del productor.
     * @param producto Producto a quitar.
     */
    public void quitarProducto(Producto producto) {
        this.productos.remove(producto);
        this.extensionTotal -= producto.getExtension();
        this.numProductos--;
    }
}
