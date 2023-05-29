/**
 * Clase que representa a un gran productor.
 */
public class GranProductor extends Productor{

    /**
     * Constructor de la clase GranProductor.
     * @param nombre Nombre del gran productor.
     * @param cooperativa Cooperativa a la que pertenece el gran productor.
     */
    public GranProductor(String nombre, Cooperativa cooperativa) {
        super(nombre, cooperativa);
        cooperativa.getGranProductores().add(this);
    }
    
    /**
     * Metodo que determina si un productor es gran productor.
     * @return true si el productor es gran productor, false en caso contrario.
     */
    public boolean esGranProductor(){
        return extensionTotal > LIMITE || numProductos > 5;
    }

    /**
     * Metodo que agrega un producto a la lista de productos del gran productor.
     * @param producto Producto a agregar.
     * @param extension Extension del producto a agregar.
     * @param granProductor Gran productor que agrega el producto.
     */
    public void agregarProducto(Producto producto, double extension, GranProductor granProductor){
        if(extension > producto.getExtension()) {
            System.out.println("No se puede agregar esa extension; supera la extension del producto.");
            return;
        }
        if (esGranProductor()){
            producto.agregarProductor(granProductor);
            producto.setExtension(producto.getExtension() - extension);
            productos.add(producto);
            setExtensionPorProducto(producto, extension);
            setExtensionTotal(extensionTotal + extension);
            setNumProductos(numProductos + 1);
        }
    }
}
