package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnalizadorCanciones {
    public static ListaReproduccion leerArchivoCSV(String ruta) throws IOException {
        ListaReproduccion lista = new ListaReproduccion();
        if (!Files.exists(Paths.get(ruta))) {
            throw new IOException("El archivo no se encuentra en la ruta especificada: " + ruta);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // Saltar la cabecera
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length >= 9) { // Asegurar que haya al menos 9 campos
                    // Índices de los campos relevantes basados en tu descripción
                    String nombre = campos[3].replaceAll("^\"|\"$", ""); // track_name, eliminar comillas
                    String artista = campos[2].replaceAll("^\"|\"$", ""); // artist_names, eliminar comillas
                    try {
                        int streams = Integer.parseInt(campos[8].replaceAll("^\"|\"$", "")); // streams, eliminar comillas y convertir a entero
                        Cancion cancion = new Cancion(nombre, artista, streams);
                        lista.agregarCancion(cancion);
                    } catch (NumberFormatException e) {
                        // Ignorar esta línea si no se puede convertir a entero
                        System.err.println("No se pudo convertir a entero: " + campos[8]);
                    }
                } else {
                    System.err.println("La línea no tiene suficientes campos: " + linea);
                }
            }
        }
        return lista;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: AnalizadorCanciones <rutaArchivo1> <rutaArchivo2>");
            return;
        }

        try {
            ListaReproduccion lista1 = leerArchivoCSV(args[0]);
            ListaReproduccion lista2 = leerArchivoCSV(args[1]);

            Set<Cancion> cancionesEnComun = lista1.encontrarCancionesEnComun(lista2);
            Cancion cancionMasReproducida = lista1.encontrarCancionMasReproducida(lista2);

            System.out.println("Canciones en común:");
            for (Cancion cancion : cancionesEnComun) {
                System.out.println(cancion);
            }

            System.out.println("\nCanción más reproducida:");
            System.out.println(cancionMasReproducida);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}