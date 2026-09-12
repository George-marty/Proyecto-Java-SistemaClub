package com.example.servicio.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.example.modelo.Club;
import com.example.servicio.ClubServicio;

public class ClubServicioImpl implements ClubServicio{
    @Override 
    public Club registrarClub(){
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Club club = new Club();

        String nombre = sc.nextLine();
        club.setNombre(nombre);

        int socios = sc.nextInt();
        club.setSocios(socios);

        sc.nextLine();

        boolean activo = sc.nextBoolean();
        club.setActivo(activo);

        LocalDate fechaFun = null;

        String fechaStr = sc.nextLine();
        fechaFun = LocalDate.parse(fechaStr, FORMATTER);

        club.setFechaFundacion(fechaFun);




        
        
        sc.close();

        return club;
    }

    
}
