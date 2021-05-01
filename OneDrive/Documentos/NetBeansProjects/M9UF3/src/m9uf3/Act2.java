/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9uf3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class Act2 {
     public static void main (String[] args) {
         Scanner scan = new Scanner(System.in);
		InetAddress dir = null;
		System.out.println("=====================================================");
		System.out.println("SORTIDA PER A LOCALHOST");
		
		try {
		
                       String url = null;
                       System.out.println("Digues la url:");
                       url = scan.nextLine();
					System.out.println("=====================================================");
			System.out.println("SORTIDA PER A URL");
			dir = InetAddress.getByName(url);
			provaTots(dir);
			
			//Array tipus InetAddress amb totes les adreces IP de google.com
			System.out.println("\tAdreces IP per a: "+dir.getHostName());
			InetAddress[] adreces = InetAddress.getAllByName(dir.getHostName());
			for (int i=0; i<adreces.length; i++)
				System.out.println("\t\t"+adreces[i].toString());
			System.out.println("=====================================================");
			
		} catch (UnknownHostException e1) {e1.printStackTrace();}
		
	}
    	private static void provaTots(InetAddress dir) {
		
		InetAddress dir2;
		
		System.out.println("\tMètode getByName(): "+dir);
		
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMètode getLocalHost(): "+dir2);
		} catch (UnknownHostException e) {e.printStackTrace();}
		
		//FEM SERVIR MÊTODES DE LA CLASSE
		System.out.println("\tMètode getHostName(): "+dir.getHostName());
		System.out.println("\tMètode getHostAddress(): "+dir.getHostAddress());
		System.out.println("\tMètode toString(): "+dir.toString());
		System.out.println("\tMètode getCanonicalHostName(): "+dir.getCanonicalHostName());
                	System.out.println("\tgetPort: "+dir.getPort());
		
	}

    
}
