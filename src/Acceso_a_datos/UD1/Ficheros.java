package Acceso_a_datos.UD1;

import java.io.*;
import java.util.Scanner;

/*
Diseña y desarrolla una aplicación que va a trabajar con ficheros de texto:
• Diseñamos un menú con:
o Introducir texto.
o Traspasar texto.
o Salir.
• En la primera ejecución del programa, no debe existir ningún fichero. • Vamos a necesitar 2 ficheros uno denominado INPUT.TXT
y otro llamado OUTPUT.TXT. • Se le va a pedir al usuario que introduzca texto por teclado, si ya existe INPUT.TXT se  debe anexionar
al final la nueva entrada.
• Al escoger traspasar texto, se debe pasar la información de INPUT.TXT a OUTPUT.TXT. • Salir, será la opción para terminar el programa.

 */

public class Ficheros {
    public static void main(String[] args) {
        crearFicheros(); // Creacion de ficheros si no existen
        mostrarMenu(); //Mostrar menu
    }


    public static void mostrarMenu() {
        int opcionMenu;
        Scanner sc = new Scanner(System.in);


        do {
            System.out.println("""
                    Menú de Ficheros.
                    Eligge una opcion:
                    1.Introducir texto.
                    2.Traspasar texto
                    0.Salir""");

            opcionMenu = sc.nextInt();

            switch (opcionMenu) {
                case 0:
                    System.out.println("Fin del programa");
                    break;
                case 1:
                    System.out.println("Has elegido introducir texto");
                    sc.nextLine(); //salto de linea
                    String texto = sc.nextLine();

                    escribirEnFichero("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\INPUT.txt", texto);
                    break;
                case 2:
                    System.out.println("Has elegido traspasar texto");
                    traspasarTexto();
                    break;

                default:
                    System.out.println("Opcion no valida, vuelva ha escribir una opcion");

            }
        } while (opcionMenu != 0); // continuar bucle mientras que o se elija la opcio de salir

    }

    //metodo para crear los ficheros si no existen
    public static void crearFicheros() {
        File outputFile = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\OUTPUT.txt");
        File intputFile = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\INPUT.txt");

        try {

            //Creacion del fichero output si no existe
            if (outputFile.createNewFile()) {
                System.out.println("Fichero output creado : " + outputFile.getName());
            } else {
                System.out.println("El fichero Output ya existe");
            }

            // Creacion del fichero input si no existe
            if (intputFile.createNewFile()) {
                System.out.println("Fichero input creado : " + intputFile.getName());
            } else {
                System.out.println("El fichero Input ya existe");
            }
        } catch (IOException e) {
            System.out.println("Error al crear ficheros");
            e.printStackTrace();
        }

    }

    //metodo para escribir en los ficheros
    public static void escribirEnFichero(String archivo, String texto) {
        File file = new File(archivo);
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(texto);
            bw.newLine();
            System.out.println("Nuevo texto escrito a el arrchivo :" + archivo);

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + archivo);
            e.printStackTrace();
        }
    }

    //metodo para traspasar el texto de INPUT.txt a OUTPUT.txt
    public static void traspasarTexto() {
        File inputFile = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\INPUT.txt");
        File outputFile = new File("C:\\Users\\arami\\Desktop\\DAM\\ACCESO A DATOS\\UD1\\OUTPUT.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileWriter fw = new FileWriter(outputFile, true);
             BufferedWriter bw = new BufferedWriter(fw)
        ) {
            //leer cada linea de input y escribirla en output
            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine(); //nueva linea en output
            }
            System.out.println("Texto traspasado de archivo input.txt a output.txt");
        } catch (IOException e) {
            System.out.println("Error al traspasar el texto");
            e.printStackTrace();
        }


    }


}