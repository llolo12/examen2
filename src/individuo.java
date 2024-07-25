import java.time.LocalDate;
import java.time.Period;


class individuo {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String ocupacion;
    private String gustoMusical;
    private String creenciaReligiosa;
    private String gustoCulinario;
    private String genero;

    /**
     * Constructor completo para crear un individuo.
     */
    public individuo(String nombre, LocalDate fechaNacimiento, String ocupacion, String gustoMusical,
                     String creenciaReligiosa, String gustoCulinario, String genero) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        this.gustoMusical = gustoMusical;
        this.creenciaReligiosa = creenciaReligiosa;
        this.gustoCulinario = gustoCulinario;
        this.genero = genero;
    }

    // Métodos getter y setter para todas las propiedades

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getGustoMusical() { return gustoMusical; }
    public void setGustoMusical(String gustoMusical) { this.gustoMusical = gustoMusical; }

    public String getCreenciaReligiosa() { return creenciaReligiosa; }
    public void setCreenciaReligiosa(String creenciaReligiosa) { this.creenciaReligiosa = creenciaReligiosa; }

    public String getGustoCulinario() { return gustoCulinario; }
    public void setGustoCulinario(String gustoCulinario) { this.gustoCulinario = gustoCulinario; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    /**
     * Método para obtener la edad del individuo basada en su fecha de nacimiento.
     */
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Edad: %d, Ocupación: %s, Gusto Musical: %s, " +
                        "Creencia Religiosa: %s, Gusto Culinario: %s, Género: %s",
                nombre, getEdad(), ocupacion, gustoMusical, creenciaReligiosa, gustoCulinario, genero);
    }
}
