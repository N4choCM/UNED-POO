import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase Cooperativa
 */
public class Cooperativa {
    private String nombre;
    private ArrayList<Producto> productos;
    private Map<String, Double> ventasTotalesPorKgPorProducto;
    private Map<String, Double> beneficiosPorProductorPorProducto;
    private Map<String, Double> beneficiosPorEmpresaLogistica;
    private Map<String, Double> beneficioTotalPorProducto;
    private ArrayList<Cliente> clientes;
    private ArrayList<PequenoProductor> pequenoProductores;
    private ArrayList<EmpresaLogistica> empresasLogisticas;
    private ArrayList<GranProductor> granProductores;
    private ArrayList<Pedido> pedidos;
    private ArrayList<ProductorFederado> productoresFederados;
    private ArrayList<Productor> productores;
    private ArrayList<ProductoNoPerecedero> productosNoPerecederos;
    private ArrayList<ProductoPerecedero> productosPerecederos;
    Map<Date, Double> ventasPorFecha = new HashMap<Date, Double>();

    /**
     * Constructor de la clase Cooperativa
     * @param nombre Nombre de la cooperativa
     */
    public Cooperativa(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<Producto>();
        this.ventasTotalesPorKgPorProducto = new HashMap<String, Double>();
        this.clientes = new ArrayList<Cliente>();
        this.pequenoProductores = new ArrayList<PequenoProductor>();
        this.empresasLogisticas = new ArrayList<EmpresaLogistica>();
        this.granProductores = new ArrayList<GranProductor>();
        this.pedidos = new ArrayList<Pedido>();
        this.productoresFederados = new ArrayList<ProductorFederado>();
        this.productores = new ArrayList<Productor>();
        this.productosNoPerecederos = new ArrayList<ProductoNoPerecedero>();
        this.productosPerecederos = new ArrayList<ProductoPerecedero>();
        this.beneficiosPorProductorPorProducto = new HashMap<String, Double>();
        this.beneficiosPorEmpresaLogistica = new HashMap<String, Double>();
        this.beneficioTotalPorProducto = new HashMap<String, Double>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Map<String, Double> getVentasTotalesPorKgPorProducto() {
        return ventasTotalesPorKgPorProducto;
    }

    public void setVentasTotalesPorKgPorProducto(Map<String, Double> ventasTotalesPorKgPorProducto) {
        this.ventasTotalesPorKgPorProducto = ventasTotalesPorKgPorProducto;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<PequenoProductor> getPequenoProductores() {
        return pequenoProductores;
    }

    public void setPequenoProductores(ArrayList<PequenoProductor> pequenoProductores) {
        this.pequenoProductores = pequenoProductores;
    }

    public ArrayList<EmpresaLogistica> getEmpresasLogisticas() {
        return empresasLogisticas;
    }

    public void setEmpresasLogisticas(ArrayList<EmpresaLogistica> empresasLogisticas) {
        this.empresasLogisticas = empresasLogisticas;
    }

    public Map<String, Double> getBeneficiosPorProductorPorProducto() {
        return beneficiosPorProductorPorProducto;
    }

    public void setBeneficiosPorProductorPorProducto(Map<String, Double> beneficiosPorProductorPorProducto) {
        this.beneficiosPorProductorPorProducto = beneficiosPorProductorPorProducto;
    }

    public Map<String, Double> getBeneficiosPorEmpresaLogistica() {
        return beneficiosPorEmpresaLogistica;
    }

    public void setBeneficiosPorEmpresaLogistica(Map<String, Double> beneficiosPorEmpresaLogistica) {
        this.beneficiosPorEmpresaLogistica = beneficiosPorEmpresaLogistica;
    }

    public Map<String, Double> getBeneficioTotalPorProducto() {
        return beneficioTotalPorProducto;
    }

    public void setBeneficioTotalPorProducto(Map<String, Double> beneficioTotalPorProducto) {
        this.beneficioTotalPorProducto = beneficioTotalPorProducto;
    }

    public ArrayList<GranProductor> getGranProductores() {
        return granProductores;
    }

    public void setGranProductores(ArrayList<GranProductor> granProductores) {
        this.granProductores = granProductores;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<ProductorFederado> getProductoresFederados() {
        return productoresFederados;
    }

    public void setProductoresFederados(ArrayList<ProductorFederado> productoresFederados) {
        this.productoresFederados = productoresFederados;
    }

    public ArrayList<Productor> getProductores() {
        return productores;
    }

    public void setProductores(ArrayList<Productor> productores) {
        this.productores = productores;
    }

    public ArrayList<ProductoNoPerecedero> getProductosNoPerecederos() {
        return productosNoPerecederos;
    }

    public void setProductosNoPerecederos(ArrayList<ProductoNoPerecedero> productosNoPerecederos) {
        this.productosNoPerecederos = productosNoPerecederos;
    }

    public ArrayList<ProductoPerecedero> getProductosPerecederos() {
        return productosPerecederos;
    }

    public void setProductosPerecederos(ArrayList<ProductoPerecedero> productosPerecederos) {
        this.productosPerecederos = productosPerecederos;
    }

    /**
     * Método que añade un producto a la lista de productos de la cooperativa
     * @param producto Producto a añadir
     */
    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    /**
     * Método que añade un cliente a la lista de clientes de la cooperativa
     * @param cliente Cliente a añadir
     */
    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    /**
     * Método que añade un pequeño productor a la lista de pequeños productores de la cooperativa
     * @param pequenoProductor Pequeño productor a añadir
     */
    public void addPequenoProductor(PequenoProductor pequenoProductor) {
        this.pequenoProductores.add(pequenoProductor);
    }

    /**
     * Método que añade una empresa logística a la lista de empresas logísticas de la cooperativa
     * @param empresaLogistica Empresa logística a añadir
     */
    public void addEmpresaLogistica(EmpresaLogistica empresaLogistica) {
        this.empresasLogisticas.add(empresaLogistica);
    }

    /**
     * Método que añade un gran productor a la lista de grandes productores de la cooperativa
     * @param granProductor Gran productor a añadir
     */
    public void addGranProductor(GranProductor granProductor) {
        this.granProductores.add(granProductor);
    }

    /**
     * Método que añade un pedido a la lista de pedidos de la cooperativa
     * @param pedido Pedido a añadir
     */
    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    /**
     * Método que añade un productor federado a la lista de productores federados de la cooperativa
     * @param productorFederado Productor federado a añadir
     */
    public void addProductorFederado(ProductorFederado productorFederado) {
        this.productoresFederados.add(productorFederado);
    }

    /**
     * Método que añade un productor a la lista de productores de la cooperativa
     * @param productor Productor a añadir
     */
    public void addProductor(Productor productor) {
        this.productores.add(productor);
    }

    /**
     * Método que añade un producto no perecedero a la lista de productos no perecederos de la cooperativa
     * @param productoNoPerecedero Producto no perecedero a añadir
     */
    public void addProductoNoPerecedero(ProductoNoPerecedero productoNoPerecedero) {
        this.productosNoPerecederos.add(productoNoPerecedero);
    }

    /**
     * Método que añade un producto perecedero a la lista de productos perecederos de la cooperativa
     * @param productoPerecedero Producto perecedero a añadir
     */
    public void addProductoPerecedero(ProductoPerecedero productoPerecedero) {
        this.productosPerecederos.add(productoPerecedero);
    }

    /**
     * Método que genera el informe anual de la cooperativa
     */
    public void generarInforme() {
        for (Producto producto : productos) {
            beneficioTotalPorProducto.put(producto.getNombre(), producto.getBeneficiosTotales());
            ventasTotalesPorKgPorProducto.put(producto.getNombre(), producto.getBeneficiosTotales()/producto.getKgVendidos());
            ventasPorFecha = producto.getVentasPorFecha();
        }

        for(Productor productor : productores) {
            beneficiosPorProductorPorProducto.put(productor.getNombre(), productor.getBeneficios());
        }

        for(EmpresaLogistica empresaLogistica : empresasLogisticas) {
            beneficiosPorEmpresaLogistica.put(empresaLogistica.getNombre(), empresaLogistica.getBeneficios());
        }

        System.out.println("---------------------------------------");
        System.out.println("Informe anual de " + this.nombre);
        System.out.println("---------------------------------------");
        System.out.println("Ventas totales por kg de producto (en Euros/kg): \n" + ventasTotalesPorKgPorProducto);
        System.out.println("Evolución de precios por producto (en Euros): \n" + ventasPorFecha);
        System.out.println("Beneficios por productor por producto (en Euros): \n" + beneficiosPorProductorPorProducto);
        System.out.println("Beneficio por empresa logística (en Euros): \n" + beneficiosPorEmpresaLogistica);
    }   
}
