package mezcladirecta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class MezclaDirecta {

    /**
     * Toma secuencias desde dos archivos auxiliares (f1 y f2) que ya están ordenadas
     * en bloques de tamaño L, las va comparando bloque a bloque y las guarda
     * ya mezcladas y ordenadas en un archivo de salida.
     */
    public static void intercalarBloques(String archivoF1, String archivoF2,
                                         String archivoSalida, int L) {
        try (
                BufferedReader lector1 = new BufferedReader(new FileReader(archivoF1));
                BufferedReader lector2 = new BufferedReader(new FileReader(archivoF2));
                PrintWriter escritor = new PrintWriter(new FileWriter(archivoSalida))
        ) {
            String linea1 = lector1.readLine();
            String linea2 = lector2.readLine();

            int elementosSacados1 = 0;
            int elementosSacados2 = 0;

            while (linea1 != null && linea2 != null) {

                if (elementosSacados1 < L && elementosSacados2 < L) {

                    int val1 = Integer.parseInt(linea1.trim());
                    int val2 = Integer.parseInt(linea2.trim());

                    if (val1 <= val2) {
                        escritor.println(val1);
                        linea1 = lector1.readLine();
                        elementosSacados1++;
                    } else {
                        escritor.println(val2);
                        linea2 = lector2.readLine();
                        elementosSacados2++;
                    }

                } else if (elementosSacados1 >= L && elementosSacados2 < L) {
                    escritor.println(Integer.parseInt(linea2.trim()));
                    linea2 = lector2.readLine();
                    elementosSacados2++;

                } else if (elementosSacados2 >= L && elementosSacados1 < L) {
                    escritor.println(Integer.parseInt(linea1.trim()));
                    linea1 = lector1.readLine();
                    elementosSacados1++;

                } else {
                    elementosSacados1 = 0;
                    elementosSacados2 = 0;
                }
            }

            while (linea1 != null) {
                escritor.println(Integer.parseInt(linea1.trim()));
                linea1 = lector1.readLine();
            }

            while (linea2 != null) {
                escritor.println(Integer.parseInt(linea2.trim()));
                linea2 = lector2.readLine();
            }

            System.out.println("Bloque intercalado con éxito!");

        } catch (IOException e) {
            System.out.println("Error al leer archivos: " + e.getMessage());
        }
    }
}