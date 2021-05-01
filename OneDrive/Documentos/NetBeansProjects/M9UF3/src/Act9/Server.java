/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9uf3;

/**
 *
 * @author Raul
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server extends Thread{
	 static Scanner scan = new Scanner (System.in);
         static Socket clienteEnlazado = null;
	 static PrintWriter fsortida = null;
	 static BufferedReader fentrada = null;
         static String cadena = "";
	public static void main (String[] args) throws Exception {
                
                
		int numeroClientes;
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		
		System.out.println("Introduce el maximo de clientes:");		                
                numeroClientes = scan.nextInt();

                //Thread thread = null;
                
                
		
		for (int j = 0; j < numeroClientes; j++) {
			

			System.out.println("Esperando conexion ");
			clienteEnlazado = servidor.accept();
			System.out.println("Cliente " + (j+1) + " connectado ");
                          
                         // thread.run();
                       Server s = new Server(); 
                       s.start();
		
                }
	
		//TANCAR STREAMS I SOCKETS
		//System.out.println("Cerrando conexión");			
		//servidor.close();
		
	}
        @Override
        public void run()  {
            try{
            //FLUX DE SORTIDA AL CLIENT
		 fsortida = new PrintWriter(clienteEnlazado.getOutputStream(), true);
		
		
		//FLUX D'ENTRADA DEL CLIENT
		 fentrada = new BufferedReader(new InputStreamReader(clienteEnlazado.getInputStream()));
		
		while ((cadena = fentrada.readLine()) != null){			
			fsortida.println(cadena);
			System.out.println("Rebent: "+cadena);
			if (cadena.equals("*")) break;
                    }
                        fentrada.close();
                        fsortida.close();
                        clienteEnlazado.close();
		}catch(Exception e){
        }
    }
}
        
        
        

    
