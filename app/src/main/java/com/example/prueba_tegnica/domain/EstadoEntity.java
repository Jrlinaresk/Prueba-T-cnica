package com.example.prueba_tegnica.domain;

public class EstadoEntity {
    private String estado;
    private int imagenResId;

    public EstadoEntity(String estado, int imagenResId) {
        this.estado = estado;
        this.imagenResId = imagenResId;
    }

    public String getEstado() {
        return estado;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
