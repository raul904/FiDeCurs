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


    private BufferedReader fentrada;
    private JTextField chat;
    private String user;

    Socket clientes;


    static String mostraChat;


    chatUpdate(String name, BufferedReader fentrada, JTextField chat, Socket Cliente) {
        this.user = name;
        this.fentrada = fentrada;
        this.chat = chat;
        this.clientes = Cliente;

    }

    @Override
    public void run() {
        try {
            fentrada = new BufferedReader(new InputStreamReader(clientes.getInputStream()));
            while ((mostraChat = fentrada.readLine()) != null) {

                System.out.println(mostraChat);
                chat.setText(mostraChat);

            }
        } catch (IOException ex) {
            Logger.getLogger(chatUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
