package org.example;

import java.util.*;

public class ListaReproduccion {
    private List<Cancion> canciones;

    public ListaReproduccion() {
        this.canciones = new ArrayList<>();
    }

    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public Set<Cancion> encontrarCancionesEnComun(ListaReproduccion otraLista) {
        Set<Cancion> set = new HashSet<>(this.canciones);
        set.retainAll(otraLista.getCanciones());
        return set;
    }

    public Cancion encontrarCancionMasReproducida(ListaReproduccion otraLista) {
        Map<Cancion, Integer> conteoStreams = new HashMap<>();

        for (Cancion cancion : this.canciones) {
            conteoStreams.put(cancion, conteoStreams.getOrDefault(cancion, 0) + cancion.getStreams());
        }

        for (Cancion cancion : otraLista.getCanciones()) {
            conteoStreams.put(cancion, conteoStreams.getOrDefault(cancion, 0) + cancion.getStreams());
        }

        return Collections.max(conteoStreams.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}