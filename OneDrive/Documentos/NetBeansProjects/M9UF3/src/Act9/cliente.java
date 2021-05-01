/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act9;

/**
 *
 * @author Raul
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.*;
import java.io.*;


/**
 *
 * @author Raul
 */
public class cliente {
	public static void main (String[] args) throws Exception {
		String user = null;
		String host = "localhost";
		int port = 60000;//Port remot
		Socket cliente = new Socket(host, port);
		
		//FLUX DE SORTIDA AL SERVIDOR
		PrintWriter fsortida = new PrintWriter(cliente.getOutputStream(), true);
		
		//FLUX D'ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		
		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
                System.out.println("user name");
                user = in.readLine();
		String cadena, eco = "";
		System.out.println("Introdueix la cadena: ");
		//Lectura teclat
		cadena = in.readLine();
		
		while (!cadena.equals("")){
			
			//Enviament cadena al servidor
			fsortida.println(user+":"+cadena);
			//Rebuda cadena del servidor
			eco = fentrada.readLine();
			System.out.println("  =>ECO: "+eco);
			//Lectura del teclat
			cadena = in.readLine();
			
			
		}
		
		fsortida.close();
		fentrada.close();
		in.close();
		cliente.close();
		
	}
		
}
