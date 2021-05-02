/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act10;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private Socket Cliente;
    //itera cada vez que añades un cliente
    private int numCliente;
    //Scanner
    
    static Scanner scan = new Scanner(System.in);
    static Socket clienteEnlazado;
    static int numeroClientes;
    static PrintWriter fsortidas[];
    static BufferedReader fentradas[];
    static String cadena = "";
    static Server clients[];
    static int contadorFin;

    //Constructor servidor
    private Server(Socket clienteEnlazado, int j) throws IOException {
        this.Cliente = clienteEnlazado;
        this.numCliente = j;
        fsortidas[j] = new PrintWriter(Cliente.getOutputStream(), true);
        fentradas[j] = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
    }

    public static void main(String[] args) throws Exception {

        int numeroClientes;
        int numPort = 60000;
        ServerSocket servidor = null;

        System.out.println("Introduce el maximo de clientes:");
        numeroClientes = scan.nextInt();

        Server.clients = new Server[numeroClientes];
        Server.fsortidas = new PrintWriter[numeroClientes];
        Server.fentradas = new BufferedReader[numeroClientes];
        servidor = new ServerSocket(numPort);

        System.out.println("Server Start");

        for (int j = 0; j < numeroClientes; j++) {

            System.out.println("Esperando conexion ");

            if (servidor != null) {
                clienteEnlazado = servidor.accept();

                System.out.println("Cliente " + (j + 1) + " connectado ");

                // Creamos el hilo
                clients[j] = new Server(clienteEnlazado, j);

                clients[j].start();

            }

        }

    }

    @Override
    public void run() {
        try {

            if (fentradas[numCliente] != null) {
                while ((cadena = fentradas[numCliente].readLine()) != null) {
                    //System.out.println(numCliente);3

                    if (cadena.contains("Desconectado")) {
                        contadorFin++;
                        System.out.println(contadorFin);
                        if (contadorFin == 1) {
                          System.exit(0);
                        }
                    }

                    for (int i = 0; i <= numCliente; i++) {
                        fsortidas[i].println(cadena);
                        System.out.println(cadena);
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
