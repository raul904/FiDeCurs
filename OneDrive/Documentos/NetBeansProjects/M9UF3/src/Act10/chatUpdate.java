/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act10;

import java.awt.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Raul
 */
public class chatUpdate extends Thread {
    
     String host = "localhost";
		int port = 60000;//Port remot
		Socket cliente;
     private BufferedReader fentrada;
     private JTextField chat;
     private String user;
        cliente = new Socket(host, port);
       static String mostraChat;
        


 

//    chatUpdate(BufferedReader fentrada, JTextField chat) {
//       this.fentrada = fentrada;
//       this.chat = chat;
//    }

    chatUpdate(String name, BufferedReader fentrada, JTextField chat) {
       this.fentrada = fentrada;
       this.chat = chat;
       this.user = user;
    }
 
        
         @Override
        public void run()  {
         try {
             fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             while ((mostraChat = fentrada.readLine()) != null){
                 
                 System.out.println(mostraChat);
                 chat.setText(mostraChat);
                 
             }
         } catch (IOException ex) {
             Logger.getLogger(chatUpdate.class.getName()).log(Level.SEVERE, null, ex);
         }
    
        
        }
    
}
