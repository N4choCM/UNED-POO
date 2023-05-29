import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

/**
 * Clase que representa un pedido de un cliente a la cooperativa.
 */
public class Pedido {
    private Long id;
    private ArrayList<Producto> productos;
    private Cliente cliente;
    private double precioTotal;

    /**
     * Constructor de la clase Pedido.
     * @param cliente Cliente que realiza el pedido.
     * @param cooperativa Cooperativa a la que se le realiza el pedido.
     */
    public Pedido(Cliente cliente, Cooperativa cooperativa) {
        this.id = System.currentTimeMillis() / 1000;
        this.productos = new ArrayList<>();
        this.cliente = cliente;
        this.precioTotal = 0;
        cooperativa.getPedidos().add(this);
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Método que efectúa una compra de un producto como Distribuidor.
     * @param cantidadEnKg Cantidad de producto en kg que se quiere comprar.
     * @param producto Producto que se quiere comprar.
     * @param fechaEntrega Fecha de entrega del producto.
     * @param fechaActual Fecha actual.
     * @param empresaTransporte Empresa de transporte que se encarga de la entrega.
     */
    public void comprarProductoDistribuidor(int cantidadEnKg, Producto producto, LocalDate fechaEntrega, LocalDate fechaActual, EmpresaLogistica empresaTransporte) {
        fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(10);
        LocalDate fechaRevision = fechaLimite.minusDays(10);

        if(cantidadEnKg < 1000) {
            System.out.println("La cantidad mínima de compra es de 1000 kg.");
            return;
        }

        if(fechaEntrega.isAfter(fechaLimite)) {
            System.out.println("La fecha de entrega es superior a 10 días contando desde hoy, " + fechaActual + ". Recuerda que hay que revisar el precio del producto 10 días antes de la fecha de entrega. Es decir, se tendrá que revisar el producto antes del " + fechaRevision + ".");
        }

        double costoTransporte = producto.getCostoTransporte();
        double precioProductoSinIva = producto.getPrecioPorKgSinIva();

        producto.setPesoEnKg(producto.getPesoEnKg() - cantidadEnKg);
        
        pagarProductor(producto);

        producto.setKgVendidos(producto.getKgVendidos() + cantidadEnKg);

        double beneficioCooperativa = precioProductoSinIva * 1.05 - precioProductoSinIva;
        producto.setBeneficiosTotales(beneficioCooperativa);

        double precioFinal = precioProductoSinIva * cantidadEnKg * 1.05 + costoTransporte;

        System.out.println("El precio final de la compra del producto " + producto.getNombre() + " es de " + precioFinal + "Euros.");

        pagarEmpresaTransporte(empresaTransporte, costoTransporte);    }

    /**
     * Método que efectúa una compra de un producto como Consumidor Final.
     * @param cantidadEnKg Cantidad de producto en kg que se quiere comprar.
     * @param producto Producto que se quiere comprar.
     * @param fechaEntrega Fecha de entrega del producto.
     * @param fechaActual Fecha actual.
     * @param empresaTransporte Empresa de transporte que se encarga de la entrega.
     */
    public void comprarProductoConsumidorFinal(int cantidadEnKg, Producto producto, LocalDate fechaEntrega, LocalDate fechaActual, EmpresaLogistica empresaTransporte) {
        fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(10);
        LocalDate fechaRevision = fechaLimite.minusDays(10);
        final double INCREMENTO_IVA = 1.10;

        if(cantidadEnKg > 100) {
            System.out.println("La cantidad máxima de compra es de 100 kg.");
            return;
        }
        if(fechaEntrega.isAfter(fechaLimite)) {
            System.out.println("La fecha de entrega es superior a 10 días contando desde hoy, " + fechaActual + ". Recuerda que hay que revisar el precio del producto 10 días antes de la fecha de entrega. Es decir, se tendrá que revisar el producto antes del " + fechaRevision + ".");
        }

        double costoTransporte = producto.getCostoTransporte();
        double precioProductoSinIva = producto.getPrecioPorKgSinIva();

        producto.setPesoEnKg(producto.getPesoEnKg() - cantidadEnKg);
        
        pagarProductor(producto);

        producto.setKgVendidos(producto.getKgVendidos() + cantidadEnKg);

        double beneficioCooperativa = precioProductoSinIva * 1.15 - precioProductoSinIva;
        producto.setBeneficiosTotales(beneficioCooperativa);

        double precioFinal = precioProductoSinIva * INCREMENTO_IVA * cantidadEnKg * 1.15 + costoTransporte;

        System.out.println("El precio final de la compra del producto " + producto.getNombre() + " es de " + precioFinal + "Euros.");

        pagarEmpresaTransporte(empresaTransporte, costoTransporte);
    }

    /**
     * Método que efectúa el pago de la parte proporcional de los beneficios 
     * de una compra para un productor.
     * @param producto Producto que se ha comprado.
     */
    public void pagarProductor(Producto producto){
        ArrayList<Productor> productores = producto.getProductores();
        Map<Productor, Double> extensionPorProductor = new HashMap<Productor, Double>();
        for (Productor productor : productores) {
            Map<Producto, Double> extensionPorProducto = productor.getExtensionPorProducto();
            extensionPorProductor.put(productor, extensionPorProducto.get(producto));
        }

        // Calcular extensión total entre todos los productores.
        double totalExtension = 0.0;
        for (double extension : extensionPorProductor.values()) {
            totalExtension += extension;
        }

        // Calcular la cantidad a pagar de cada productor.
        Map<Productor, Double> pagosProporcionales = new HashMap<>();
        for (Map.Entry<Productor, Double> entry : extensionPorProductor.entrySet()) {
            Productor productor = entry.getKey();
            double extension = entry.getValue();
            double proporcion = extension / totalExtension;
            double pagoProporcional = proporcion * 1000.0;
            pagosProporcionales.put(productor, pagoProporcional);
        }

        // Pagar a cada productor.
        for (Map.Entry<Productor, Double> entry : pagosProporcionales.entrySet()) {
            Productor productor = entry.getKey();
            double pagoProporcional = entry.getValue();
            productor.setBeneficios(pagoProporcional);
        }
    }

    /**
     * Método que efectúa el pago de la parte proporcional de los beneficios
     * de una compra para una empresa de transporte.
     * @param empresaTransporte Empresa de transporte que se encarga de la entrega.
     * @param precioFinal Precio final de la compra.
     */
    public void pagarEmpresaTransporte(EmpresaLogistica empresaTransporte, double costoTransporte) {
        empresaTransporte.setBeneficios(empresaTransporte.getBeneficios() + costoTransporte);
    }
}
