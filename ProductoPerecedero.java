/**
 * Clase ProductoPerecedero que hereda de Producto y que contiene los atributos y métodos específicos de los productos perecederos.
 */
public class ProductoPerecedero extends Producto {
    private final double COSTO_POR_KM_PEQUENA_LOGISTICA = 0.5;
    private final double COSTO_POR_KM_GRAN_LOGISTICA = 0.35;

    /**
     * Constructor de la clase ProductoPerecedero.
     * @param nombre Nombre del producto.
     * @param precioPorKgSinIva Precio por kilogramo sin IVA del producto.
     * @param pesoEnKg Peso en kilogramos del producto.
     * @param extension Extensión en Ha del producto.
     * @param perecedero Indica si el producto es perecedero o no.
     * @param cooperativa Cooperativa a la que pertenece el producto.
     */
    public ProductoPerecedero(String nombre, double precioPorKgSinIva, double pesoEnKg, double extension, boolean perecedero, Cooperativa cooperativa) {
        super(nombre, precioPorKgSinIva, pesoEnKg, extension, perecedero);
        cooperativa.getProductos().add(this);
        cooperativa.getProductosPerecederos().add(this);
    }

    /**
     * Método que calcula el costo de transporte de un producto perecedero.
     * @param distancia Distancia en kilómetros a la que se transportará el producto.
     * @param producto Producto a transportar.
     * @param empresaLogistica Empresa logística que transportará el producto.
     * @param cantidadEnKg Cantidad en kilogramos del producto a transportar.
     * @param descuento Descuento que se aplicará al costo de transporte (puede ser 0).
     */
    public void setCostoTransporte(double distancia, Producto producto, EmpresaLogistica empresaLogistica, double cantidadEnKg, double descuento) {
        int numeroDeTrayectos = calcularTrayectos(producto.getPesoEnKg());
        int kmPequeñaLogistica = calcularKmPequenaLogistica(distancia);
        int kmGranLogistica = (int) (distancia - kmPequeñaLogistica);

        if(descuento > 0.99){
            descuento = 0;
        }

        if(distancia < 100){
            this.costoTransporte = calcularCostoPequenaLogistica(distancia, numeroDeTrayectos, producto, cantidadEnKg);
            establecerDescuento(descuento);

        }else{
            this.costoPequenaLogistica = calcularCostoPequenaLogistica(kmPequeñaLogistica, numeroDeTrayectos, producto, cantidadEnKg);
            this.costoGranLogistica = calcularCostoGranLogistica(kmGranLogistica, numeroDeTrayectos, producto, cantidadEnKg); 
            this.costoTransporte = costoPequenaLogistica + costoGranLogistica;   
            establecerDescuento(descuento);        
        }

        empresaLogistica.setBeneficios(costoTransporte);
    }

    /**
     * Método que calcula el número de trayectos que se deben realizar para transportar un producto perecedero.
     * @param peso Peso en kilogramos del producto.
     * @return Número de trayectos que se deben realizar para transportar el producto.
     */
    public int calcularTrayectos(double peso){
        return (int) (peso/1000);
    }

    /**
     * Método que calcula la distancia en kilómetros que se recorrerá en la pequeña logística.
     * @param distancia Distancia en kilómetros a la que se transportará el producto.
     * @return Distancia en kilómetros que se recorrerá en la pequeña logística.
     */
    public int calcularKmPequenaLogistica(double distancia){
        int centenas = (int) (distancia/100);
        return centenas * 100;
    }

    /**
     * Método que calcula el costo de la pequeña logística.
     * @param distancia Distancia en kilómetros a la que se transportará el producto.
     * @param numeroDeTrayectos Número de trayectos que se deben realizar para transportar el producto.
     * @param producto Producto a transportar.
     * @param cantidadEnKg Cantidad en kilogramos del producto a transportar.
     * @return Costo de la pequeña logística.
     */
    public double calcularCostoPequenaLogistica(double distancia, int numeroDeTrayectos, Producto producto, double cantidadEnKg){
        return COSTO_POR_KM_PEQUENA_LOGISTICA * distancia * numeroDeTrayectos * 0.5 * producto.getPrecioPorKgSinIva() * cantidadEnKg;
    }

    /**
     * Método que calcula el costo de la gran logística.
     * @param distancia Distancia en kilómetros a la que se transportará el producto.
     * @param numeroDeTrayectos Número de trayectos que se deben realizar para transportar el producto.
     * @param producto Producto a transportar.
     * @param cantidadEnKg Cantidad en kilogramos del producto a transportar.
     * @return Costo de la gran logística.
     */
    public double calcularCostoGranLogistica(double distancia, int numeroDeTrayectos, Producto producto, double cantidadEnKg){
        return COSTO_POR_KM_GRAN_LOGISTICA * distancia * numeroDeTrayectos * 0.5 * producto.getPrecioPorKgSinIva() * cantidadEnKg;
    }

    /**
     * Método que establece el descuento que se aplicará al costo de transporte.
     * @param descuento Descuento que se aplicará al costo de transporte.
     */
    public void establecerDescuento(double descuento){
        this.costoTransporte = costoTransporte * (1 - descuento);
    }
}
