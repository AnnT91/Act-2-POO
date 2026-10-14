import java.util.ArrayList;

/**
 * Programa principal: instancia objetos y prueba los métodos de las clases.
 */
public class Main {
    public static void main(String[] args) {

        // Instanciación de objetos usando el constructor con parámetros
        Estudiante e1 = new Estudiante("Ana López", "A001");
        Estudiante e2 = new Estudiante("Carlos Ruiz", "A002");
        Estudiante e3 = new Estudiante(); // constructor vacío

        // Se asignan datos al tercer estudiante con setters
        e3.setNombre("María Pérez");
        e3.setMatricula("A003");

        // Uso del método agregarCalificacion
        e1.agregarCalificacion(90);
        e1.agregarCalificacion(85);
        e2.agregarCalificacion(60);
        e2.agregarCalificacion(65);
        e3.agregarCalificacion(100);
        e3.agregarCalificacion(95);
        e3.agregarCalificacion(120); // inválida, prueba la validación

        // Creación del curso e inscripción de estudiantes
        Curso poo = new Curso("Programación Orientada a Objetos", "Dr. Martínez");
        poo.inscribir(e1);
        poo.inscribir(e2);
        poo.inscribir(e3);

        // Reporte final
        System.out.println();
        poo.mostrarReporte();
    }
}

/**
 * Clase Estudiante: representa a un alumno con su matrícula y calificaciones.
 */
class Estudiante {

    // ----- Atributos -----
    private String nombre;
    private String matricula;
    private ArrayList<Double> calificaciones;

    // ----- Constructores -----

    /** Constructor completo: recibe nombre y matrícula. */
    public Estudiante(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.calificaciones = new ArrayList<>();
    }

    /** Constructor vacío: crea un estudiante con valores por defecto. */
    public Estudiante() {
        this("Sin nombre", "000000");
    }

    // ----- Métodos -----

    /** Agrega una calificación (válida entre 0 y 100). */
    public void agregarCalificacion(double calificacion) {
        if (calificacion >= 0 && calificacion <= 100) {
            calificaciones.add(calificacion);
        } else {
            System.out.println("Calificación inválida: " + calificacion);
        }
    }

    /** Calcula el promedio de las calificaciones registradas. */
    public double calcularPromedio() {
        if (calificaciones.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (double c : calificaciones) {
            suma += c;
        }
        return suma / calificaciones.size();
    }

    /** Indica si el estudiante aprueba (promedio >= 70). */
    public boolean estaAprobado() {
        return calcularPromedio() >= 70;
    }

    /** Muestra la información del estudiante en consola. */
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Calificaciones: " + calificaciones);
        System.out.printf("Promedio: %.2f%n", calcularPromedio());
        System.out.println("Estado: " + (estaAprobado() ? "Aprobado" : "Reprobado"));
        System.out.println("-----------------------------");
    }

    // ----- Getters y setters -----
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}

/**
 * Clase Curso: agrupa estudiantes y permite consultar datos del grupo.
 */
class Curso {

    // ----- Atributos -----
    private String nombre;
    private String profesor;
    private ArrayList<Estudiante> estudiantes;

    // ----- Constructor -----
    public Curso(String nombre, String profesor) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.estudiantes = new ArrayList<>();
    }

    // ----- Métodos -----

    /** Inscribe un estudiante en el curso. */
    public void inscribir(Estudiante estudiante) {
        estudiantes.add(estudiante);
        System.out.println(estudiante.getNombre() + " inscrito en " + nombre);
    }

    /** Calcula el promedio general del grupo. */
    public double promedioGrupal() {
        if (estudiantes.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Estudiante e : estudiantes) {
            suma += e.calcularPromedio();
        }
        return suma / estudiantes.size();
    }

    /** Busca al estudiante con mejor promedio. */
    public Estudiante mejorEstudiante() {
        Estudiante mejor = null;
        for (Estudiante e : estudiantes) {
            if (mejor == null || e.calcularPromedio() > mejor.calcularPromedio()) {
                mejor = e;
            }
        }
        return mejor;
    }

    /** Muestra el reporte completo del curso. */
    public void mostrarReporte() {
        System.out.println("===== CURSO: " + nombre + " | Profesor: " + profesor + " =====");
        for (Estudiante e : estudiantes) {
            e.mostrarInfo();
        }
        System.out.printf("Promedio del grupo: %.2f%n", promedioGrupal());
        Estudiante mejor = mejorEstudiante();
        if (mejor != null) {
            System.out.println("Mejor estudiante: " + mejor.getNombre());
        }
    }
}