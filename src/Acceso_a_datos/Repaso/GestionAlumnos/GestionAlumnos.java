package Acceso_a_datos.Repaso.GestionAlumnos;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

public class GestionAlumnos {
    private static final String FICHERO_ALUMNOS = "C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\ALUMNOS.DAT";


    public static void main(String[] args) {
        eliminarFichero();
        int opcionMenu;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("""
                    _________________________________________
                    
                    1. Introducir Información sobre un alumno.
                    2. Mostrar información de alumnos a base de la nota de corte.
                    0. Salir
                    
                    _________________________________________
                    
                    """);

            opcionMenu = sc.nextInt();
            sc.nextLine();

            switch (opcionMenu) {
                case 0:
                    System.out.println("Fin del programa");
                    break;
                case 1:
                    System.out.println("Has elegido introducir información sobre un alumno");
                    introducirAlumno(sc);
                    break;
                case 2:
                    System.out.println("Has elegido mostrar la informacion :");
                    mostrarAlumnosConNotaDeCorte(sc);
                    break;
                default:
                    System.out.println("Opción no válida, vuelva a escribir una opción");
                    eliminarFichero();
            }

        } while (opcionMenu != 0);
    }


    private static void eliminarFichero() {
        File alumnosFile = new File(FICHERO_ALUMNOS);

        if (alumnosFile.delete()) {
            System.out.println("Anterior archivo de datos eliminado\n");
        } else {
            System.out.println("Ningún archivo eliminado");
        }
    }


    private static void introducirAlumno(Scanner scanner) {
        System.out.print("Introduce DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Introduce nombre completo: ");
        String nombreCompleto = scanner.nextLine();

        System.out.print("Introduce fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacStr = scanner.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Introduce fecha de matriculación (dd/MM/yyyy): ");
        String fechaMatricStr = scanner.nextLine();
        LocalDate fechaMatriculacion = LocalDate.parse(fechaMatricStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Introduce la media del expediente: ");
        double mediaExpediente = scanner.nextDouble();
        scanner.nextLine();  // Consumir el salto de línea

        Alumno alumno = new Alumno(dni, nombreCompleto, fechaNacimiento, fechaMatriculacion, mediaExpediente);

        // Añadir al fichero
        File fichero = new File(FICHERO_ALUMNOS);
        try (FileOutputStream fos = new FileOutputStream(fichero, true);
             ObjectOutputStream oos = fichero.exists() && fichero.length() > 0 ?
                     new ObjectOutputStream(fos) {
                         protected void writeStreamHeader() throws IOException {
                             reset(); // No escribir encabezado si ya existe contenido
                         }
                     } :
                     new ObjectOutputStream(fos)) {

            oos.writeObject(alumno);
            System.out.println("Alumno añadido con éxito.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero.");
        }
    }

    private static void mostrarAlumnosConNotaDeCorte(Scanner scanner) {
        System.out.print("Introduce la nota de corte: ");
        double notaDeCorte = scanner.nextDouble();
        scanner.nextLine();  // Consumir el salto de línea

        List<Alumno> alumnos = leerAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos en el fichero.");
        } else {
            for (Alumno alumno : alumnos) {
                if (alumno.getMediaExpediente() > notaDeCorte) {
                    System.out.println(alumno.getDni() + " - " + alumno.getNombreCompleto());
                }
            }
        }
    }

    private static List<Alumno> leerAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        File fichero = new File(FICHERO_ALUMNOS);
        if (fichero.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
                while (true) {
                    try {
                        Alumno alumno = (Alumno) ois.readObject();
                        alumnos.add(alumno);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el fichero.");
            }
        }
        return alumnos;
    }

}

