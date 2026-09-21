package com.example.servicio.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.modelo.Actividad;
import com.example.servicio.ActividadServicio;

public class ActividadServicioImpl implements ActividadServicio {
    @Override 
    public Actividad registrarActividad(){
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Actividad actividad = new Actividad();

        System.out.print("ingrese su nombre: ");
        String nombre = sc.nextLine();
        actividad.setNombre(nombre);
        System.out.print("Ingrese el cupo: ");
        int cupo = sc.nextInt();
        actividad.setCupo(cupo);

        
        System.out.print("Esta disponible: ");
        boolean disponible = sc.nextBoolean();
        actividad.setDisponible(disponible);

        LocalDate fechaIni = null;
        System.out.print("Ingrese la fecha: ");
        String fechaStr = sc.nextLine();
        fechaIni = LocalDate.parse(fechaStr,FORMATTER);

        actividad.setFechaInicio(fechaIni);

        sc.close();

        return actividad;
    }
    public void guardarActividad(Actividad actividad, ArrayList<Actividad>listactividad){
        listactividad.add(actividad);
    }

}
