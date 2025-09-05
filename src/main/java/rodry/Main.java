package rodry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        generarAlumnos();
    }

    public static void generarAlumnos(){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno("Juan Perez", 8, "Programacion"));
        listaAlumnos.add(new Alumno("Sonia Diaz", 6, "Lengua"));
        listaAlumnos.add(new Alumno("Rodrigo Perez", 7, "Lengua"));
        listaAlumnos.add(new Alumno("Raquel Gomez", 6, "Programacion"));
        listaAlumnos.add(new Alumno("Estefania Lopez", 9, "Matemática II"));
        listaAlumnos.add(new Alumno("Hernan Segoviano", 5, "Matemática II"));
        listaAlumnos.add(new Alumno("Roberto Gomez Bolaños", 9, "Ingles III"));
        listaAlumnos.add(new Alumno("Carlos Villagran", 8, "Ingles III"));

        //Obtener nombre de los alumnos aprobados con >= 7 y en mayúsculas
        System.out.println("Alumnos aprobados: ");
        listaAlumnos.stream()
                    .filter( s -> (s.getNota() >= 7))
                    .sorted((a1, a2) -> Integer.compare(a2.getNota(), a1.getNota()))
                    .forEach(alumno -> System.out.println(alumno.getNombre() + " Nota: " + alumno.getNota()));

        //Promedio General
        double promedioGeneral =
                listaAlumnos.stream()
                        .mapToDouble(Alumno::getNota)
                        .average()
                        .orElse(0);

        System.out.println("\nEl promedio general de notas es: "+ promedioGeneral);

        //Agrupar alummos por curso usando groupBy
        System.out.println("\nAlumnos por curso");
        Map<String, List<Alumno>> alumnosPorCurso =
                listaAlumnos.stream()
                        .collect(Collectors.groupingBy(Alumno::getCurso));
        alumnosPorCurso.forEach((key, alumnos) -> {
            System.out.println(key + " - Alumnos:");
            alumnos.forEach(alumno -> System.out.println(alumno.getNombre() + " - " + alumno.getNota()));
        });

        //Obtener los 3 mejores promedios
        System.out.println("\nMejores 3 promedios");
        List<Alumno> mejoresPromedios = listaAlumnos.stream()
                .sorted((a1, a2) -> Integer.compare(a2.getNota(), a1.getNota()))
                .limit(3)
                .collect(Collectors.toList());

        mejoresPromedios.forEach(alumno -> System.out.println(alumno.getNombre() + " Nota: " +  alumno.getNota()));
    }
}