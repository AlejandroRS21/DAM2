package Acceso_a_datos.Repaso;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ficheros2 implements Serializable {
    private static final long serialVersionUID = 1L; // Importante para la serialización
    private String dni;
    private String nombre;
    private Date f_nacimiento;
    private Date f_matriculacion;
    private double media_expediente;

    // Constructor
    public Ficheros2(String dni, String nombre, Date fNacimiento, Date fMatriculacion, double mediaExpediente) {
        this.dni = dni;
        this.nombre = nombre;
        this.f_nacimiento = fNacimiento;
        this.f_matriculacion = fMatriculacion;
        this.media_expediente = mediaExpediente;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public double getMedia_expediente() {
        return media_expediente;
    }

    public static void main(String[] args) {
        eliminarFichero();
        mostrarMenu();
    }

    // Método que elimina el archivo de alumnos si existe
    private static void eliminarFichero() {
        File alumnosFile = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\ALUMNOS.DAT");

        if (alumnosFile.delete()) {
            System.out.println("Anterior archivo de datos eliminado\n");
        } else {
            System.out.println("Ningún archivo eliminado");
        }
    }

    // Método para mostrar el menú de opciones
    private static void mostrarMenu() {
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

            switch (opcionMenu) {
                case 0:
                    System.out.println("Fin del programa");
                    break;
                case 1:
                    System.out.println("Has elegido introducir información sobre un alumno");
                    introducirInformacion();  // Llama al método para introducir la información
                    break;
                case 2:
                    System.out.println("Has elegido mostrar la informacion :");
                    mostrarInformacion();  // Llama al método para mostrar los alumnos que superan la nota de corte
                    break;
                default:
                    System.out.println("Opción no válida, vuelva a escribir una opción");
            }

        } while (opcionMenu != 0);
    }

    // Método para introducir la información de un alumno y guardarla en un fichero binario
    private static void introducirInformacion() {
        Scanner sc = new Scanner(System.in);
        String dni, nombre;
        Date fNacimiento, fMatriculacion;
        double mediaExpediente;

        try {
            // Pide los datos al usuario
            System.out.println("Introduce el DNI del alumno:");
            dni = sc.nextLine();
            System.out.println("Introduce el nombre del alumno:");
            nombre = sc.nextLine();
            System.out.println("Introduce la fecha de nacimiento del alumno en formato dd/MM/yyyy:");
            fNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
            System.out.println("Introduce la fecha de matriculación del alumno en formato dd/MM/yyyy:");
            fMatriculacion = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
            System.out.println("Introduce la media del expediente:");
            mediaExpediente = sc.nextDouble();

            // Crear el objeto alumno
            Ficheros2 alumno = new Ficheros2(dni, nombre, fNacimiento, fMatriculacion, mediaExpediente);

            // Usa try-with-resources para manejar el archivo
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\ALUMNOS.DAT", true))) {
                oos.writeObject(alumno);
                System.out.println("Alumno introducido correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Usa el formato dd/MM/yyyy.");
        }
    }

    // Método para mostrar los alumnos que superen una nota de corte
    private static void mostrarInformacion() {
        File file = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\ALUMNOS.DAT");

        // Comprueba si el archivo existe
        if (!file.exists()) {
            System.out.println("El archivo no existe. Primero debes introducir información de alumnos.");
            return; // Sale del método si el archivo no existe
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la nota de corte:");
        double notaCorte = sc.nextDouble();

        // Usa try with resources para manejar el archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Ficheros2 alumno = (Ficheros2) ois.readObject();
                if (alumno.getMedia_expediente() > notaCorte) {
                    System.out.println("DNI: " + alumno.getDni() + " - Nombre: " + alumno.getNombre());
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin de la lista de alumnos.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
