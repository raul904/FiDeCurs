/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9uf3;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Raul Yangshun
 */
public class Activitat3 {
	public static void main (String[] args) {
            String text;
            String cerca;
            int campos;  
            String linea;
            boolean contiene = false;
             Scanner scan = new Scanner(System.in);
		try {           
                        
                        System.out.println("Introdueix la url: ");
                        cerca = scan.nextLine();
                        
                        System.out.println("Introdueix el Text: ");
                        text = scan.nextLine();
                        
                        System.out.println("Introdueix el numero de camps que vols: ");                      
                        campos = scan.nextInt();

			URL url = new URL(cerca);
			URLConnection connexio = url.openConnection();
			
			//Obtener Campos
			System.out.println("===============================================================");
			System.out.println("Camps del 1 al "+campos+" de la Capçalera");
                        for(int i = 1;i<=campos;i++){
			System.out.println("getHeaderField("+i+")=> " + connexio.getHeaderField(i));
          
			System.out.println("===============================================================");
                        }
                        
			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
                        int numeroDeLinea = 1;
                        contiene = true;
                                //Leer html
                                while ((pagina.readLine()) != null) {
                                    linea = pagina.readLine();  //se lee una línea
                                    if (linea != null) {
                                        if(linea.contains(text)){
                                             contiene = true;
                                             System.out.println("Linea " + numeroDeLinea + ": " + linea);
                                        }
                                    }
                                           numeroDeLinea++;
                                }
                                if(contiene==false){ //si no contienen el texto se muestra un mensaje indicándolo
                        System.out.println(text + " no se ha encontrado en el texto");
                    } else {
                                    System.out.println("Programa acabada");
                                }
                   }
                
                        catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                }
}

