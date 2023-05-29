/**
 * Clase que representa un producto no perecedero.
 */
public class ProductoNoPerecedero extends Producto{
    private final double COSTO_POR_KM_PEQUENA_LOGISTICA = 0.01;
    private final int COSTO_POR_TRAMO_GRAN_LOGISTICA = 250;

    /**
     * Constructor de la clase ProductoNoPerecedero.
     * @param nombre Nombre del producto no perecedero.
     * @param precioPorKgSinIva Precio por kilogramo sin IVA del producto no perecedero.
     * @param pesoEnKg Peso en kilogramos del producto no perecedero.
     * @param extension Extensión en Ha del producto no perecedero.
     * @param perecedero Indica si el producto es perecedero o no.
     * @param cooperativa Cooperativa a la que pertenece el producto no perecedero.
     */
    public ProductoNoPerecedero(String nombre, double precioPorKgSinIva, double pesoEnKg, double extension, boolean perecedero, Cooperativa cooperativa) {
        super(nombre, precioPorKgSinIva, pesoEnKg, extension, perecedero);
        cooperativa.getProductos().add(this);
        cooperativa.getProductosNoPerecederos().add(this);
    }

    /**
     * Metodo que calcula el costo de transporte de un producto no perecedero.
     * @param distancia Distancia en km a la que se transporta el producto no perecedero.
     * @param producto Producto no perecedero a transportar.
     * @param empresaLogistica Empresa logistica que transporta el producto no perecedero.
     * @param cantidadEnKg Cantidad en kilogramos del producto no perecedero a transportar.
     * @param descuento Descuento aplicado al costo de transporte (puede ser 0).
     */
    public void setCostoTransporte(double distancia, Producto producto, EmpresaLogistica empresaLogistica, double cantidadEnKg, double descuento) {
        int numeroDeTrayectos = calcularTrayectos(producto.getPesoEnKg());
        int numeroDeTramosDeGranLogistica = calcularTramosGranLogistica(distancia);
        int kmPequeñaLogistica = (int) (distancia - (numeroDeTramosDeGranLogistica * 50));

        if(descuento > 0.99){
            descuento = 0;
        }

        if(distancia < 100){
            this.costoTransporte = calcularCostoPequenaLogistica(distancia, numeroDeTrayectos, cantidadEnKg);
            establecerDescuento(descuento);

        }else{
            this.costoPequenaLogistica = calcularCostoPequenaLogistica(kmPequeñaLogistica, numeroDeTrayectos, cantidadEnKg);
            this.costoGranLogistica = calcularCostoGranLogistica(numeroDeTramosDeGranLogistica, numeroDeTrayectos, producto, cantidadEnKg); 
            this.costoTransporte = costoPequenaLogistica + costoGranLogistica;   
            establecerDescuento(descuento);        
        }
        
        empresaLogistica.setBeneficios(costoTransporte);
    }

    /**
     * Metodo que calcula el numero de trayectos que se deben realizar para transportar un producto no perecedero.
     * @param peso Peso en kilogramos del producto no perecedero.
     * @return Numero de trayectos que se deben realizar para transportar el producto no perecedero.
     */
    public int calcularTrayectos(double peso){
        return (int) (peso/1000);
    }

    /**
     * Metodo que calcula el numero de tramos de gran logistica que se deben realizar para transportar un producto no perecedero.
     * @param distancia Distancia en km a la que se transporta el producto no perecedero.
     * @return Numero de tramos de gran logistica que se deben realizar para transportar el producto no perecedero.
     */
    public int calcularTramosGranLogistica(double distancia){
        return (int) (distancia/50);
    }

    /**
     * Metodo que calcula el costo de transporte de un producto no perecedero que se transporta por pequeña logistica.
     * @param distancia Distancia en km a la que se transporta el producto no perecedero.
     * @param numeroDeTrayectos Numero de trayectos que se deben realizar para transportar el producto no perecedero.
     * @param cantidadEnKg Cantidad en kilogramos del producto no perecedero a transportar.
     * @return Costo de transporte de un producto no perecedero que se transporta por pequeña logistica.
     */
    public double calcularCostoPequenaLogistica(double distancia, int numeroDeTrayectos, double cantidadEnKg){
        return COSTO_POR_KM_PEQUENA_LOGISTICA * distancia * numeroDeTrayectos * cantidadEnKg;
    }

    /**
     * Metodo que calcula el costo de transporte de un producto no perecedero que se transporta por gran logistica.
     * @param numeroDeTramosDeGranLogistica Numero de tramos de gran logistica que se deben realizar para transportar el producto no perecedero.
     * @param numeroDeTrayectos Numero de trayectos que se deben realizar para transportar el producto no perecedero.
     * @param producto Producto no perecedero a transportar.
     * @param cantidadEnKg Cantidad en kilogramos del producto no perecedero a transportar.
     * @return Costo de transporte de un producto no perecedero que se transporta por gran logistica.
     */
    public double calcularCostoGranLogistica(int numeroDeTramosDeGranLogistica, int numeroDeTrayectos, Producto producto, double cantidadEnKg){
        return COSTO_POR_TRAMO_GRAN_LOGISTICA * numeroDeTramosDeGranLogistica  * numeroDeTrayectos * 0.5 * producto.getPrecioPorKgSinIva() * cantidadEnKg;
    }

    /**
     * Metodo que establece el descuento aplicado al costo de transporte de un producto no perecedero.
     * @param descuento Descuento aplicado al costo de transporte de un producto no perecedero.
     */
    public void establecerDescuento(double descuento){
        this.costoTransporte = costoTransporte * (1 - descuento);
    }

}
