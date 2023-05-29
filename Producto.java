import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;

/**
 * Clase abstracta Producto
 */
public abstract class Producto {
    private String nombre;
    private double precioPorKgSinIva;
    private double pesoEnKg;
    private double extension;
    private double rendimientoPorHa;
    private boolean perecedero;
    private double kgVendidos;
    private double beneficiosTotales;
    private Map<Date, Double> ventasPorFecha;
    protected ArrayList<Productor> productores;
    protected double costoPequenaLogistica;
    protected double costoGranLogistica;
    protected double costoTransporte;

    /**
     * Constructor de la clase Producto
     * @param nombre Nombre del producto
     * @param precioPorKgSinIva Precio por kilogramo sin IVA
     * @param pesoEnKg Peso en kilogramos
     * @param extension Extensión en Ha
     * @param perecedero Indica si el producto es perecedero o no
     */
    public Producto(String nombre, double precioPorKgSinIva, double pesoEnKg, double extension, boolean perecedero){
        this.nombre = nombre;
        this.precioPorKgSinIva = precioPorKgSinIva;
        this.pesoEnKg = pesoEnKg;
        this.extension = extension;
        this.rendimientoPorHa = extension / pesoEnKg;
        this.perecedero = perecedero;
        this.productores = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public double getPrecioPorKgSinIva() {
        return precioPorKgSinIva;
    }

    public double getPesoEnKg() {
        return pesoEnKg;
    }

    public double getExtension() {
        return extension;
    }

    public double getRendimientoPorHa() {
        rendimientoPorHa = pesoEnKg / extension;
        return rendimientoPorHa;
    }

    public ArrayList<Productor> getProductores() {
        return productores;
    }

    public boolean isPerecedero() {
        return perecedero;
    }

    public double getKgVendidos() {
        return kgVendidos;
    }

    public double getBeneficiosTotales() {
        return beneficiosTotales;
    }

    public Map<Date, Double> getVentasPorFecha() {
        return ventasPorFecha;
    }

    public double getCostoPequenaLogistica() {
        return costoPequenaLogistica;
    }

    public double getCostoGranLogistica() {
        return costoGranLogistica;
    }

    public double getCostoTransporte() {
        return costoTransporte;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioPorKgSinIva(double precioPorKgSinIva) {
        this.precioPorKgSinIva = precioPorKgSinIva;
    }

    public void setPesoEnKg(double pesoEnKg) {
        this.pesoEnKg = pesoEnKg;
    }

    public void setExtension(double extension) {
        this.extension = extension;
    }

    public void setRendimientoPorHa(double rendimientoPorHa) {
        this.rendimientoPorHa = rendimientoPorHa;
    }

    public void setPerecedero(boolean perecedero) {
        this.perecedero = perecedero;
    }

    public void setKgVendidos(double kgVendidos) {
        this.kgVendidos = kgVendidos;
    }

    public void setBeneficiosTotales(double beneficiosTotales) {
        this.beneficiosTotales = beneficiosTotales;
    }

    public void setVentasPorFecha(Map<Date, Double> ventasPorFecha) {
        this.ventasPorFecha = ventasPorFecha;
    }

    /**
     * Método que actualiza el precio del producto
     * @param precioPorKgSinIva Precio por kilogramo sin IVA
     * @param fechaActual Fecha actual
     */
    public void actualizarPrecio(double precioPorKgSinIva, LocalDate fechaActual) {
        this.precioPorKgSinIva = precioPorKgSinIva;
        fechaActual = LocalDate.now();
        Date sqlDate = Date.valueOf(fechaActual);
        this.getVentasPorFecha().put(sqlDate, precioPorKgSinIva);
    }

    /**
     * Método que agrega un productor a la lista de productores
     * @param productor Productor a agregar
     */
    public void agregarProductor(Productor productor) {
        productores.add(productor);
    }
}
