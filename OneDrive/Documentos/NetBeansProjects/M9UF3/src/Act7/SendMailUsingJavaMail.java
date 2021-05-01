/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act7;

/**
 *
 * @author Raul
 */

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SendMailUsingJavaMail {
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
		try {   
			final String username;
			final String password;
                        String destinatario, asunto, body;
                        
                        System.out.println("Introduiex el teu email:");
                        username = scan.nextLine();
                        System.out.println("Contrasenya:");
                        password = scan.nextLine();
                        System.out.println("Destinatari:");
                        destinatario = scan.nextLine();
                        System.out.println("Asumpte:");
                        asunto = scan.nextLine();
                        System.out.println("Body");
                        body = scan.nextLine();
                        
			
			JavaMailSender sender = new JavaMailSender("smtp.gmail.com", 587);
			
			sender.addRecipient(destinatario);
			//sender.addRecipient("algumes@xtec.cat");
			sender.setSender(username);
			sender.setSubject(asunto);
			sender.setMailText(body);
			//sender.sendUsingTLSAuthentication(username, password);
			sender.sendUsingSSLAuthentication(username, password);
			//sender.sendUsingAuthentication(username, password);
			sender.send();
		} catch (Exception ex) {
			Logger.getLogger(SendMailUsingJavaMail.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	}

    
}
