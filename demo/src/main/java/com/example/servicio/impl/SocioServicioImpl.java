package com.example.servicio.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.modelo.Club;
import com.example.modelo.Socio;
import com.example.servicio.SocioServicio;

public class SocioServicioImpl implements SocioServicio{
    @Override 
    public Socio registrarSocio(){
        Scanner sc = new Scanner(System.in);
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Socio socio = new Socio();
        Club club = new Club();


        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        socio.setNombre(nombre);
        


        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine();
        socio.setApellido(apellido);
        
        System.out.print("Ingrese dni: ");
        String dni = sc.nextLine();
        socio.setDni(dni);

        System.out.print("Ingrese email: ");
        String email = sc.nextLine();
        socio.setEmail(email);
        
        System.out.print("Ingrese la fecha de alta: ");
        LocalDate fechaAlt = null;
        String fechaStr = sc.nextLine();
        fechaAlt = LocalDate.parse(fechaStr, FORMATTER);
        socio.setFechaAlta(fechaAlt);
        
        club.getNombre();

        sc.close();
        return socio;
    }
    @Override
    public void guardarSocio(Socio socio, ArrayList<Socio> listaSocio){
        listaSocio.add(socio);
    }
    @Override
    public void mostrarSocio(ArrayList<Socio> listaSocio){
        for (Socio socio : listaSocio) {
            System.out.println(socio.toString());
        }
        
    }

}
