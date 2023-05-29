/**
 * Clase que representa a un productor federado.
 */
public class ProductorFederado extends Productor{

    /**
     * Constructor de la clase ProductorFederado.
     * @param nombre Nombre del productor federado.
     * @param cooperativa Cooperativa a la que pertenece el productor federado.
     */
    public ProductorFederado(String nombre, Cooperativa cooperativa) {
        super(nombre, cooperativa);
        cooperativa.getProductoresFederados().add(this);
    }
    
    /**
     * Metodo que determina si un productor es productor federado.
     * @return true si el productor es productor federado, false en caso contrario.
     */
    public boolean esProductorFederado(){
        return extensionTotal <= LIMITE && numProductos == 1;
    }

    /**
     * Metodo que agrega un producto a la lista de productos del productor federado.
     * @param producto Producto a agregar.
     * @param extension Extension del producto a agregar.
     * @param productorFederado Productor federado al que se agrega el producto.
     */
    public void agregarProducto(Producto producto, double extension, ProductorFederado productorFederado){
        if(extension > producto.getExtension()) {
            System.out.println("No se puede agregar esa extension; supera la extension del producto.");
            return;
        }
        if (esProductorFederado()){
            producto.agregarProductor(productorFederado);
            producto.setExtension(producto.getExtension() - extension);
            productos.add(producto);
            setExtensionPorProducto(producto, extension);
            setExtensionTotal(extensionTotal + extension);
            setNumProductos(numProductos + 1);
        }
    }
}
