package Acceso_a_datos.Repaso;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ficheros2 {
    String dni;
    String nombre;
    String f_nacimiento;
    String f_matricullacion;
    String media_expediente ;

    public static void main(String[] args) {
        eliminarFichero();
        mostrarMenu();
    }

    private static void eliminarFichero() {
        File alumnosFile = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\ALUMNOS.DAT");

        if(alumnosFile.delete()){
            System.out.println("Anterior archivo de datos eliminado\n");
        }else{
            System.out.println("Ningun Archivo eliminado");
        }
    }

    private static void mostrarMenu() {
        int opcionMenu;

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                    _________________________________________
                    
                    1.Introduciir Informacion sobre un alumno.
                    2.Mostrar informacion de alumnos a base de la nota de corte
                    0.Salir    
                    
                    _________________________________________
                    
                    """);
            opcionMenu = sc.nextInt();

            switch (opcionMenu) {
                case 0:
                    System.out.println("Fin del programa");
                    break;
                case 1:
                    System.out.println("Has elegido introducir informacion sobre un alumno");
                    introducirInformacion();
                    break;
                case 2:
                    System.out.println("Introduce una nota de corte");
                    mostrarInformacion();
                    break;


                default:
                    System.out.println("Opcion no valida, vuelva ha escribir una opcion");

            }


        }while (opcionMenu!=0);
    }

    private static void mostrarInformacion() {

    }

    private static void introducirInformacion() {

        try{

                System.out.println("Introduce el dni del alumno");
                System.out.println("Introduce el nombre del alumno");
                System.out.println("Introduce la fecha de nacimiento del alumno");
                System.out.println("Introduce la fecha de matriculacion del alumno");

        }catch (IOException e){

        }




    }


}
