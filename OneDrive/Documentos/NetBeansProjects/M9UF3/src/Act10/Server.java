/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act10;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server extends Thread{
    
    private Socket Cliente;
    private final int numCliente;
    
	 static Scanner scan = new Scanner (System.in);
         static Socket clienteEnlazado;
         static int numeroClientes;
	 static PrintWriter fsortida[];
	 static BufferedReader fentrada[];
         static String cadena = "";
         static Server clients[];
         static boolean primeraVez = true;
         
         
         



    private Server(Socket clienteEnlazado, int j) {
         this.Cliente = clienteEnlazado;
        this.numCliente = j;
    }

         
	public static void main (String[] args) throws Exception {
                
                
		int numeroClientes;
		int numPort = 60000;
		ServerSocket servidor = null;
		 //Socket[] sockets = new Socket[3]; //Line giving me error
		System.out.println("Introduce el maximo de clientes:");		                
                numeroClientes = scan.nextInt();

                Server.clients = new Server[numeroClientes];
                Server.fsortida = new PrintWriter[numeroClientes];
                
                
            
               
                servidor = new ServerSocket(numPort);

               
                System.out.println("Server Start");

      
                
		
		for (int j = 0; j < numeroClientes; j++) {
			 
                                 
                System.out.println("Esperando conexion ");
                        
                        if(servidor != null){
                        clienteEnlazado = servidor.accept();
                        System.out.println("Cliente "+ (j+1) + " connectado ");
                        
                        // LANZA UN HILO CON UN NUEVO CLIENTE
                    clients[j] = new Server(clienteEnlazado, j);
                    fsortida[j] = new PrintWriter(clients[j].clienteEnlazado.getOutputStream(), true);
                    clients[j].start();
                        
                        
                        }
                }
		
                       
		
                }
	
	
		
	

  
 
        @Override
        public void run()  {
            try{ 
         String user=null;
         PrintWriter fsortida = null;
         BufferedReader fentrada = null;

            //FLUX DE SORTIDA AL CLIENT
		 fsortida = new PrintWriter(clienteEnlazado.getOutputStream(), true);
		
		
		//FLUX D'ENTRADA DEL CLIENT
		 fentrada = new BufferedReader(new InputStreamReader(clienteEnlazado.getInputStream()));
		
		 if (fentrada != null) {
                while ((cadena = fentrada.readLine()) != null) {

                    //apartado para coger el nombre de login
                    if (primeraVez) {
                        fsortida.println(cadena);
                      
                        user=cadena;
                        System.out.println("User:"+cadena);
                       
                       primeraVez = false;
                    }
                    if(primeraVez==false){
                        fsortida.println(cadena);
                        System.out.println("User:"+"[ "+user+" ]"+cadena);
                        
                                          }
                }
                 }
		}catch(Exception e){
        }
    }
}
        
        
        

    
