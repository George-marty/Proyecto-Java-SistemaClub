package com.example.servicio.impl;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.DAO.ClubDAO;
import com.example.modelo.Club;
import com.example.servicio.ClubServicio;

public class ClubServicioImpl implements ClubServicio{
    @Override 
    public Club registrarClub(){
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Club club = new Club();

        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        club.setNombre(nombre);

        System.out.print("Ingrese los socios: ");
        int socios = sc.nextInt();
        club.setSocios(socios);

        
        System.out.print("Ingrese el estado: ");
        boolean activo = sc.nextBoolean();
        club.setActivo(activo);

        sc.nextLine();
        LocalDate fechaFun = null;
        System.out.print("Ingrese la fecha: ");
        String fechaStr = sc.nextLine();
        fechaFun = LocalDate.parse(fechaStr, FORMATTER);

        club.setFechaFundacion(fechaFun);
        System.out.print("Ingrese la direccion: ");
        String direccion = sc.nextLine();
        club.setDireccion(direccion);

        System.out.print("Ingrese email: ");
        String emailContacto = sc.nextLine();
        club.setEmail_contacto(emailContacto);




        
        
        sc.close();
        System.out.println(club.toString());

        return club;
    }

    @Override 
    public void guardarClub(Club club, ArrayList<Club> listaClub){
        int id = new ClubDAO().insertClub(club);
        club.setId(id);
        listaClub.add(club);
        
    }

    
}
