package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejecutable {
	
    public static void main(String[] args)  {
    	
    	System.out.println("ACCESO SECUENCIAL: ");
    	HiloSecuencial hilo1= new HiloSecuencial();
    	hilo1.start();
    	
    	try {
			hilo1.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	int numeroDeEntradas= hilo1.getNumeroEntradas();
    	int numerosPorHilo=numeroDeEntradas/5;
        int inicioHilo=0;
        int finalHilo=numerosPorHilo;
        int suma = 0;
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        System.out.println("ACCESO CONCURRENTE: ");
        
        ArrayList<HiloConcurrente> hilos= new ArrayList<HiloConcurrente>();
        long tiempo= System.currentTimeMillis();
        
        for (int i = 0; i < 5; i++) {
        	hilos.add(new HiloConcurrente(inicioHilo, finalHilo));
        	inicioHilo= inicioHilo+numerosPorHilo;
        	finalHilo=finalHilo+ numerosPorHilo;
        	if(finalHilo==10) {
        		finalHilo++;
        	}
		}
        
        for (HiloConcurrente hilo2 : hilos) {
			hilo2.start();
		}
        for (HiloConcurrente hilo2 : hilos) {
			try {
				hilo2.join();
			} catch (InterruptedException e) {
			}
			suma= suma+ hilo2.getSuma();
		}
        
        System.out.println("La suma es: "+ suma);
        System.out.println("Ha tardado: "+ (System.currentTimeMillis()- tiempo));
        
    }
}
