package org.example;

public class Cancion {
    private String nombre;
    private String artista;
    private int streams;

    public Cancion(String nombre, String artista, int streams) {
        this.nombre = nombre;
        this.artista = artista;
        this.streams = streams;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public int getStreams() {
        return streams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cancion cancion = (Cancion) o;

        if (!nombre.equals(cancion.nombre)) return false;
        return artista.equals(cancion.artista);
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + artista.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return nombre + ", " + artista;
    }
}