/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act10;

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
import java.util.Scanner;

public class ServidorTCP2 extends Thread {

    // ATRIBUTOS
    private final Socket cliente;
    private final int numCliente;
    private boolean conActiva;

    private String[] nombreArray;
    private String nombre;
    private boolean firstTime = true;

    private String[] mensajeArray;
    private String mensaje;

    private String quienEnvia;

    public static PrintWriter fSalidas[];
    public static ServidorTCP2 clientes[];

    // CONSTRUCTOR
    public ServidorTCP2(Socket clienteConectado, int i) {
        this.cliente = clienteConectado;
        this.numCliente = i;
        this.conActiva = true;
    }

    // GETTER PARA COMPROBAR SI LA CONEXION CON ESE CLIENTE HA FINALIZADO
    public boolean isConActiva() {
        return conActiva;
    }

    // RUN
    public void run() {
        nombre = "";
        this.setName("\\asdf\\");
        // VARIABLES
        String cadena;

        // FLUJO DE SALIDA Y ENTRADA AL CLIENTE
        PrintWriter fSalida = null;
        BufferedReader fEntrada = null;
        try {
            fSalida = new PrintWriter(cliente.getOutputStream(), true);
            fEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error creando flujo de salida o entrada al cliente " + numCliente);
        }

        // CONTROLA ERRORES RECIBIENDO MENSAJES DEL CLIENTE
        try {
            // VA MOSTRANDO LOS MENSAJES DEL CLIENTE HASTA QUE SE DESCONECTE
            if (fEntrada != null) {
                while ((cadena = fEntrada.readLine()) != null) {

                    //apartado para coger el nombre de login
                    if (firstTime) {
                        System.out.println("User:");
                       fSalida.println(cadena);
                       firstTime = false;
                    }
                    if (cadena.startsWith("\\login:") && firstTime) {
                        nombreArray = cadena.split(":");
                        nombre = nombreArray[1];
                        System.out.println(nombre);
                        this.setName(nombre);

                        cadena = "tu nombre de usuario es " + this.getName();
                        firstTime = false;
                        fSalida.println(cadena);
                    } else if (cadena.startsWith("\\login:") && firstTime == false) {
                        System.out.println("Este usuario ya tiene nombre");
                        cadena = "No puedes cambiar el nombre otra vez";
                        fSalida.println(cadena);
                    }
                    //ACABA apartado para coger el nombre de login

                    //apartado para enviar mensaje a todos menos a el mismo
                    if (cadena.startsWith("\\msg:")) {
                        mensajeArray = cadena.split(":");
                        mensaje = mensajeArray[1];
                        System.out.println(nombre);

                        for (int i = 0; i < clientes.length; i++) {
                            if (clientes[i] != null) {
                                if (!(this.getName().equals(clientes[i].getName()))
                                        && !(clientes[i].getName().equalsIgnoreCase("\\asdf\\"))) {
                                    //fSalida.println(cadena);
                                    //clientes[i]
                                    System.out.println("nombre del cliente" + clientes[i].getName());
                                    fSalidas[i].println(mensaje);
                                }
                            }
                        }
                    }
                    //ACABA apartado para enviar mensaje a todos menos a el mismo

                    //apartado para enviar mensaje a una persona especifica
                    if (cadena.startsWith("\\msgto:")) {
                        mensajeArray = cadena.split(":");
                        quienEnvia = mensajeArray[1];
                        mensaje = mensajeArray[2];

                        for (int i = 0; i < clientes.length; i++) {
                            if (clientes[i].getName().equalsIgnoreCase(quienEnvia)) {
                                fSalidas[i].println(this.getName() + ":" + mensaje);
                            }
                        }
                    }
                    //ACABA apartado para enviar mensaje a una persona especifica

                    //apartado para saber quien hay conectado
                    if (cadena.startsWith("\\users")) {
                        for (int i = 0; i < clientes.length; i++) {
                            if (clientes[i] != null) {
                                fSalida.println(clientes[i].getName());
                            }

                        }
                    }

                    System.out.println("Cliente " + numCliente + " - Recibiendo: " + cadena);
                    if (cadena.equals("\\logout")) {
                        break;
                    }
                }
                for (int i = 0; i < clientes.length; i++) {
                    try {
                        if (this.getName().equals(clientes[i].getName())) {
                            clientes[i] = null;
                            clientes[i].setName("");
                            fSalidas[i] = null;
                        }
                    } catch (Exception e) {

                    }

                }

            }
        } catch (IOException e) {
            System.out.println("Error en la conexion del cliente " + numCliente);
        }

        // CONTROLA DESCONEXION CLIENTE
        try {
            // CERRAR STREAMS Y SOCKETS
            if (fEntrada != null) {
                fEntrada.close();
            }
            if (fSalida != null) {
                fSalida.close();
            }
            cliente.close();
            conActiva = false;
            System.out.println("Cerrando conexión cliente..." + numCliente);
        } catch (IOException e) {
            System.out.println("Error cerrando conexion con el cliente " + numCliente);
        }
    }

    // METODO QUE COMPRUEBA SI HAN ACABADO TODAS LAS CONEXIONES CON LOS CLIENTES
    private static boolean finalServer(ServidorTCP2[] c, int totalClientes) {

        boolean fin = false;
        boolean clienteConectado = false;

        int i = 0;
        while (i < totalClientes && !clienteConectado) {

            if (c[i] != null) {
                clienteConectado = c[i].isConActiva();
            }
            fin = !clienteConectado;
            i++;
        }

        return fin;
    }

    // MAIN
    public static void main(String[] args) {

        // VARIABLES
        final int totalClientes;
        Scanner sc = new Scanner(System.in);
        final int numPort = 60000;
        ServerSocket servidor = null;

        System.out.println("cuantos clientes soporta el servidor?");
        totalClientes = sc.nextInt();

        ServidorTCP2.clientes = new ServidorTCP2[totalClientes];
        ServidorTCP2.fSalidas = new PrintWriter[totalClientes];

        // CONTROLA LA CONEXION DEL SERVIDOR
        try {
            // INICIALIZO SERVIDOR
            servidor = new ServerSocket(numPort);

            // INDICO CREACION SERVIDOR
            System.out.println("Servidor empezado");

        } catch (IOException e) {
            System.err.println("Error inicializando servidor");
        }

        // CONTROLA LA CONEXION CON EL CLIENTE
        try {
            // BUCLE N CLIENTES
            for (int i = 0; i < totalClientes; i++) {

                // ESPERA CONEXION DE UN CLIENTE
                System.out.println("Esperando conexión... ");
                Socket clienteConectado = null;
                if (servidor != null) {
                    clienteConectado = servidor.accept();
                    System.out.println("Cliente conectado... " + i);

                    // LANZA UN HILO CON UN NUEVO CLIENTE
                    clientes[i] = new ServidorTCP2(clienteConectado, i);
                    fSalidas[i] = new PrintWriter(clientes[i].cliente.getOutputStream(), true);
                    clientes[i].start();
                }
            }
        } catch (IOException e) {
            System.err.println("Error conectando con cliente");
        }

        // INDICO QUE SE HA LLEGADO AL LIMITE DE CLIENTES
        System.out.println("Limite de clientes alcanzado (" + totalClientes + ")");

        // CONTROLA LA DESCONEXION DEL SERVIDOR
        try {
            // CUANDO EL SERVIDOR HA LLEGADO AL MAXIMO DE CLIENTES, COMPRUEBA CONSTANTEMENTE
            // SI SE HAN DESCONECTADO TODOS LOS CLIENTES
            while (!finalServer(clientes, totalClientes)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (servidor != null) {
                servidor.close();
            }
            System.out.println("Cerrando conexión servidor");
        } catch (IOException e) {
            System.out.println("Error cerrando conexion del servidor");
        }
    }
}