package com.example.prueba_tegnica.domain;

// Objeto de valor del dominio
public class Respuesta {
    private final String mensaje;

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
