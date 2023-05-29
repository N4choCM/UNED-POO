/**
 * Clase que representa a una empresa logistica
 */
public class EmpresaLogistica {
    private String nombre;
    private double beneficios;

    /**
     * Constructor de la clase EmpresaLogistica
     * @param nombre Nombre de la empresa logistica
     * @param cooperativa Cooperativa a la que pertenece la empresa logistica
     */
    public EmpresaLogistica(String nombre, Cooperativa cooperativa) {
        this.nombre = nombre;
        this.beneficios = 0;
        cooperativa.getEmpresasLogisticas().add(this);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(double beneficios) {
        this.beneficios += beneficios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
