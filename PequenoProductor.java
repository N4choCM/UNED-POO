/**
 * Clase que representa a un pequeño productor.
 */
public class PequenoProductor extends Productor{

    /**
     * Constructor de la clase PequenoProductor.
     * @param nombre Nombre del productor.
     * @param cooperativa Cooperativa a la que pertenece el productor.
     */
    public PequenoProductor(String nombre, Cooperativa cooperativa) {
        super(nombre, cooperativa);
        cooperativa.getPequenoProductores().add(this);
    }
    
    /**
     * Metodo que indica si el productor es un pequeño productor.
     * @return true si el productor es pequeño productor, false en caso contrario.
     */
    public boolean esPequenoProductor(){
        return extensionTotal <= LIMITE && numProductos <= 5;
    }

    /**
     * Metodo que cede un producto a un productor federado.
     * @param producto Producto a ceder.
     * @param extension Extension del producto a ceder.
     * @param productorFederado Productor federado al que se cede el producto.
     */
    public void cederProducto(Producto producto, Double extension, ProductorFederado productorFederado){
        if (esPequenoProductor()){
            quitarProducto(producto);
            setExtensionTotal(extensionTotal - extension);
            setNumProductos(numProductos - 1);
            productorFederado.agregarProducto(producto, producto.getExtension(), productorFederado);
            productorFederado.setExtensionTotal(productorFederado.getExtensionTotal() + extension);
            productorFederado.setNumProductos(productorFederado.getNumProductos() + 1);
        }
    }

    /**
     * Metodo que agrega un producto a un pequeño productor.
     * @param producto Producto a agregar.
     * @param extension Extension del producto a agregar.
     * @param pequenoProductor Pequeño productor al que se agrega el producto.
     */
    public void agregarProducto(Producto producto, double extension, PequenoProductor pequenoProductor){
        if(extension > producto.getExtension()) {
            System.out.println("No se puede agregar esa extension; supera la extension del producto.");
            return;
        }
        if (esPequenoProductor()){
            producto.agregarProductor(pequenoProductor);
            producto.setExtension(producto.getExtension() - extension);
            productos.add(producto);
            setExtensionPorProducto(producto, extension);
            setExtensionTotal(extensionTotal + extension);
            setNumProductos(numProductos + 1);
        }
    }
}
