package com.example.servicio.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.example.modelo.Actividad;
import com.example.servicio.ActividadServicio;

public class ActividadServicioImpl implements ActividadServicio {
    @Override 
    public Actividad registrarActividad(){
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Actividad actividad = new Actividad();


        String nombre = sc.nextLine();
        actividad.setNombre(nombre);

        int cupo = sc.nextInt();
        actividad.setCupo(cupo);

        sc.nextLine();

        boolean disponible = sc.nextBoolean();
        actividad.setDisponible(disponible);

        LocalDate fechaIni = null;
        
        String fechaStr = sc.nextLine();
        fechaIni = LocalDate.parse(fechaStr,FORMATTER);

        actividad.setFechaInicio(fechaIni);

        sc.close();

        return actividad;
    }
}
