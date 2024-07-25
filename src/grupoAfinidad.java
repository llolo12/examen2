import java.util.List;
import java.util.Map;

class grupoAfinidad {
    private String nombre;
    private Map<String, Integer> criteriosAfinidad;
    private List<Individuo> miembros;

    public grupoAfinidad() {

    }

    protected void agregarCriterioAfinidad(String criterio, int valor) {
        criteriosAfinidad.put(criterio, valor);
    }

    public void agregarMiembro(Individuo individuo) {
        miembros.add(new Individuo(individuo.nombre(), individuo.fechaNacimiento(),
                individuo.ocupacion(), individuo.gustoMusical(),
                individuo.creenciaReligiosa(), individuo.gustoCulinario(),
                individuo.genero()));
    }
    public String getNombre() { return nombre; }
    public Map<String, Integer> getCriteriosAfinidad() { return criteriosAfinidad; }
    public List<Individuo> getMiembros() { return miembros; }
}
