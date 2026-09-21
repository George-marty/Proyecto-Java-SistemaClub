package com.example.servicio.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.modelo.Actividad;
import com.example.modelo.Inscripcion;
import com.example.modelo.Socio;
import com.example.servicio.InscripcionServicio;

public class InscripcionServicioImpl implements InscripcionServicio {
    @Override 
    public Inscripcion registrarInscripcion(){
        Scanner sc = new Scanner(System.in);
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Inscripcion inscripcion = new Inscripcion();
        Actividad actividad = new Actividad();
        Socio socio = new Socio();
        actividad.getNombre();
        socio.getNombre();
        
        System.out.print("Ingrese la fecha de inscripcion: ");
        LocalDate fechaIns = null;
        String fechaStr = sc.nextLine();
        fechaIns = LocalDate.parse(fechaStr,FORMATTER);
        inscripcion.setFechaInscripcion(fechaIns);

        
        



        sc.close();
        return inscripcion;
    }
    @Override 
    public void guardarInscripcion(Inscripcion inscripcion,ArrayList<Inscripcion>listaInscripcion){
        listaInscripcion.add(inscripcion);
    }
    @Override 
    public void mostrarInscripcion(ArrayList<Inscripcion>listaInscripcion){
        for (Inscripcion inscripcion : listaInscripcion) {
            System.out.println(inscripcion.toString());
        }
    }
}
