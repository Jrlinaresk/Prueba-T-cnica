package com.example.prueba_tegnica.tools;

import android.content.Context;

import com.example.prueba_tegnica.R;
import com.example.prueba_tegnica.domain.EstadoEntity;

import java.util.Random;

public class RandomStatusGenerator  {

    public static EstadoEntity generarEstadoAleatorio(Context context) {
        EstadoEntity aprobado = new EstadoEntity(context.getResources().getString(R.string.estado_aprobado), R.drawable.ic_check_circle);
        EstadoEntity rechazado = new EstadoEntity(context.getResources().getString(R.string.estado_rechazado), R.drawable.ic_cancel);
        EstadoEntity saldoInsuficiente = new EstadoEntity(context.getResources().getString(R.string.estado_saldo_insuficiente) , R.drawable.ic_warning);
        EstadoEntity errorComunicacion = new EstadoEntity(context.getResources().getString(R.string.estado_error_de_comunicación), R.drawable.ic_error);

        EstadoEntity[] estados = {aprobado, rechazado, saldoInsuficiente, errorComunicacion};

        // Obtener un índice aleatorio del array
        int indiceAleatorio = new Random().nextInt(estados.length);

        // Devolver el estado correspondiente al índice aleatorio
        return estados[indiceAleatorio];
    }
}
