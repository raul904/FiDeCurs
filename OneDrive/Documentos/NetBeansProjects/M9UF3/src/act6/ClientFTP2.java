/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act6;
import java.io.*;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;
/**
 *
 * @author Raul
 */


public class ClientFTP2 {
	
	public static void main (String[] args) {
		Scanner scan = new Scanner (System.in);
                
		//Servidor FTP
		FTPClient client = new FTPClient();
                //Servidors que he probat i funcionen
                //nic.funet.fi
                //ftp.etse.urv.es
                //ftp.urv.es
		String ServerFTP;
                System.out.println("Introdueix el server FTP: ");
                ServerFTP = scan.nextLine();
		System.out.println("Ens connectem al servidor: "+ServerFTP);
		
		//Usuari FTP
		String usuari = "anonymous";
		String contrasenya = "guest";
		
		try {
			
			client.connect(ServerFTP);
			boolean login = client.login(usuari, contrasenya);
			
			if (login)
				
				System.out.println("Login correcte... ");
				
			else {
				
				System.out.println("Login incorrecte... ");
				client.disconnect();
				System.exit(1);
				
			}
			
			System.out.println("Directori actual: "+client.printWorkingDirectory());
			FTPFile[] files = client.listFiles();
			System.out.println("Fitxers al directori actual: "+files.length);
			
			//Array par a visualitzar el tipus de fitxer
			String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};
			
			for (int i=0; i<files.length; i++) {
				
				System.out.println("\t"+files[i].getName()+"=>"+tipus[files[i].getType()]);
				
			}
			
			boolean logout = client.logout();
				
			if (logout)
				
				System.out.println("Logout del servidor FTP... ");
			
			else
				
				System.out.println("Error en fer un logout... ");
			
			client.disconnect();
			System.out.println("Desconnectat... ");
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
			
		}
		
		
		
	}

}
