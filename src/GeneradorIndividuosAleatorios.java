/*================================================================================================
Study Center....: Universidad Técnica Nacional
Campus..........: Pacífico (JRMP)
College career..: Ingeniería en Tecnologías de Información
Period..........: 2C-2024
Course..........: ITI-221 - Programación I
Document........: class_06 - functions.java
Goals...........: individual generator
Professor.......: Jorge Ruiz (york)
Student.........:
================================================================================================*/

import java.util.*;
import java.time.LocalDate;
import java.time.Period;

/**
 *Class that represents an individual with its characteristics.
 */
record Individuo(String nombre, LocalDate fechaNacimiento, String ocupacion, String gustoMusical,
                 String creenciaReligiosa, String gustoCulinario, String genero) {
    /**
     * Complete builder to create an individual.
     */
    Individuo {
    }

    // Métodos getter y setter para todas las propiedades

    /**
     *Method to obtain the age of the individual based on their date of birth.
     */
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }


    public String toString() {
        return String.format("Nombre: %s, Edad: %d, Ocupación: %s, Gusto Musical: %s, " +
                        "Creencia Religiosa: %s, Gusto Culinario: %s, Género: %s",
                nombre, getEdad(), ocupacion, gustoMusical, creenciaReligiosa, gustoCulinario, genero);
    }
}

/**
 *Main class for generating and managing individuals and affinity groups.
 */
public class GeneradorIndividuosAleatorios {
    private static final Random random = new Random();

    /**
     * Generate a random name.
     */
    public static String generarNombre() {
        String[] nombres = {"Ana", "Juan", "María", "Carlos", "Sofía", "Luis", "Elena", "Pedro", "Laura", "Miguel"};
        String[] apellidos = {"García", "Rodríguez", "Martínez", "López", "González", "Pérez", "Sánchez", "Ramírez", "Torres", "Flores"};
        return nombres[random.nextInt(nombres.length)] + " " + apellidos[random.nextInt(apellidos.length)];
    }

    /**
     *Generates a random date of birth for an age between 18 and 97 years.
     */
    public static LocalDate generarFechaNacimiento() {
        int edad = random.nextInt(80) + 18;
        return LocalDate.now().minusYears(edad).minusDays(random.nextInt(365));
    }

    /**
     * Genera una ocupación aleatoria.
     */
    public static String generarOcupacion() {
        String[] ocupaciones = {
                "Ingeniero", "Médico", "Profesor", "Abogado", "Contador",
                "Diseñador", "Chef", "Periodista", "Programador", "Electricista"
        };
        return ocupaciones[random.nextInt(ocupaciones.length)];
    }

    /**
     * Generates a random occupation.
     */
    public static String generarGustoMusical() {
        String[] gustosMusicales = {
                "Rock", "Pop", "Jazz", "Clásica", "Reggaeton", "Electrónica"
        };
        return gustosMusicales[random.nextInt(gustosMusicales.length)];
    }

    /**
     * Generates a random religious belief.
     */
    public static String generarCreenciaReligiosa() {
        String[] creenciasReligiosas = {
                "Cristianismo", "Islam", "Budismo", "Hinduismo", "Ateísmo"
        };
        return creenciasReligiosas[random.nextInt(creenciasReligiosas.length)];
    }

    /**
     * Generates a random culinary taste.
     */
    public static String generarGustoCulinario() {
        String[] gustosCulinarios = {
                "Comida italiana", "Comida mexicana", "Comida japonesa",
                "Comida vegetariana", "Comida rápida"
        };
        return gustosCulinarios[random.nextInt(gustosCulinarios.length)];
    }

    /**
     * Generates a random gender.
     */
    public static String generarGenero() {
        String[] generos = {
                "Masculino", "Femenino", "No binario", "Género fluido", "Agénero"
        };
        return generos[random.nextInt(generos.length)];
    }

    /**
     * Generates a list of random individuals.
     */
    public static List<Individuo> generarListaIndividuos(int cantidad) {
        List<Individuo> individuos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            individuos.add(new Individuo(
                    generarNombre(),
                    generarFechaNacimiento(),
                    generarOcupacion(),
                    generarGustoMusical(),
                    generarCreenciaReligiosa(),
                    generarGustoCulinario(),
                    generarGenero()
            ));
        }
        return individuos;
    }

    /**
     * Create affinity groups based on the given list of individuals.
     */
    public static List<grupoAfinidad> crearGruposAfinidad(List<Individuo> individuos) {
        List<grupoAfinidad> grupos = new ArrayList<>();

        grupoAfinidad amantesMusicales = new grupoAfinidad();
        amantesMusicales.agregarCriterioAfinidad("gustoMusical", 3);
        amantesMusicales.agregarCriterioAfinidad("edad", 2);
        amantesMusicales.agregarCriterioAfinidad("genero", 1);
        grupos.add(amantesMusicales);

        grupoAfinidad gourmets = new grupoAfinidad();
        gourmets.agregarCriterioAfinidad("gustoCulinario", 3);
        gourmets.agregarCriterioAfinidad("ocupacion", 2);
        gourmets.agregarCriterioAfinidad("creenciaReligiosa", 1);
        grupos.add(gourmets);

        grupoAfinidad profesionales = new grupoAfinidad();
        profesionales.agregarCriterioAfinidad("ocupacion", 3);
        profesionales.agregarCriterioAfinidad("edad", 2);
        profesionales.agregarCriterioAfinidad("gustoMusical", 1);
        grupos.add(profesionales);

        for (Individuo individuo : individuos) {
            for (grupoAfinidad grupo : grupos) {
                if (tieneAfinidadConGrupo(individuo, grupo)) {
                    grupo.agregarMiembro(individuo);
                }
            }
        }

        return grupos;
    }

    /**
     * Checks if an individual has affinity with a group based on the group's criteria.
     */
    private static boolean tieneAfinidadConGrupo(Individuo individuo, grupoAfinidad grupo) {
        if (grupo.getMiembros().isEmpty()) {
            return true; // El primer miembro siempre se añade al grupo
        }

        int puntajeAfinidad = 0;
        Individuo representante = grupo.getMiembros().getFirst();

        for (Map.Entry<String, Integer> criterio : grupo.getCriteriosAfinidad().entrySet()) {
            switch (criterio.getKey()) {
                case "edad":
                    if (Math.abs(individuo.getEdad() - representante.getEdad()) <= 5) {
                        puntajeAfinidad += criterio.getValue();
                    }
                    break;
                case "ocupacion":
                    if (individuo.ocupacion().equals(representante.ocupacion())) {
                        puntajeAfinidad += criterio.getValue();
                    }
                    break;
                case "gustoMusical":
                    if (individuo.gustoMusical().equals(representante.gustoMusical())) {
                        puntajeAfinidad += criterio.getValue();
                    }
                    break;
                case "creenciaReligiosa":
                    if (individuo.creenciaReligiosa().equals(representante.creenciaReligiosa())) {
                        puntajeAfinidad += criterio.getValue();
                    }
                    break;
                case "gustoCulinario":
                    if (individuo.gustoCulinario().equals(representante.gustoCulinario())) {
                        puntajeAfinidad += criterio.getValue();
                    }
                    break;
                case "genero":
                    if (individuo.genero().equals(representante.genero())) {
                        puntajeAfinidad += criterio.getValue();
                    }
                    break;
            }
        }

        return puntajeAfinidad >= 3;
    }

    /**
     * Prints affinity group information.
     */
    public static void imprimirInformacionGrupos(List<grupoAfinidad> grupos) {
        for (grupoAfinidad grupo : grupos) {
            System.out.println("Grupo: " + grupo.getNombre());
            int count = 1;
            for (Map.Entry<String, Integer> criterio : grupo.getCriteriosAfinidad().entrySet()) {
                System.out.println("Relación " + count + ": " + criterio.getKey() + " (Valor: " + criterio.getValue() + ")");
                count++;
            }
            System.out.println("Lista de Individuos:");
            for (Individuo individuo : grupo.getMiembros()) {
                System.out.println("• " + individuo.nombre());
            }
            System.out.println();
        }
    }

    /**
     *Prints the list of individuals who do not belong to any affinity group.
     */
    public static void imprimirIndividuosNoAfiliados(List<Individuo> todosIndividuos, List<grupoAfinidad> grupos) {
        Set<String> nombresAfiliados = new HashSet<>();
        for (grupoAfinidad grupo : grupos) {
            for (Individuo individuo : grupo.getMiembros()) {
                nombresAfiliados.add(individuo.nombre());
            }
        }

        System.out.println("Individuos que no pertenecen a ningún grupo de afinidad:");
        for (Individuo individuo : todosIndividuos) {
            if (!nombresAfiliados.contains(individuo.nombre())) {
                System.out.println("• " + individuo.nombre());
            }
        }
    }}

