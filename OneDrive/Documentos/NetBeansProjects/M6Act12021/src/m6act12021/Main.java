/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6act12021;

import com.microsoft.sqlserver.jdbc.spatialdatatypes.Point;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Statement stmt = null;
    static  Connection con = null;
    private static ResultSet res;
    public static void main(String[] args) throws SQLException {
             
//        Connection connection = null;
    
        String url = "jdbc:mysql://localhost:3306/m6uf2";
        String usuari = "root";
        String password = "";
       
        
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = (Connection) DriverManager.getConnection(url, usuari, password);
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        int chivato=0;
        while(chivato!=1){
        System.out.println("1-Introduir Alumne");
        System.out.println("2-Eliminar Alumne");
        System.out.println("3-Modificar alumne");
        System.out.println("4-Introduir poblacio");
        System.out.println("5-Llista els alumnes");
        System.out.println("6-Llista les poblacions");
        System.out.println("7-Llistar una sola poblacio");
        System.out.println("8-Llistar una sol alumne");
        System.out.println("9-Introduir poblacio");
        
//        listar un solo alumno
//        listar una sola poblacion
        System.out.println("10-Sortir");
           
        int Opcion;
        Opcion = sc.nextInt();
        switch(Opcion){
        
           case 1: introducirNom() ;
           break;
               
           case 2: esborrarAlumne();  
           break;
               
           case 3: modificarAlumne();
           break;
           
           case 4:afegirPoblacions();
           break;
               
           case 5:mostrarAlumnes();
           break;
               
           case 6:mostrarPobles();
           break;
               
           case 7:mostrar1Poble();
           break;
           
           case 8:mostrar1Alumne();
           break;     
               
           case 9:esborrarPoblacio();
           break;    
           case 10: 
            chivato=1;   
            sc.close();
            //stmt.close(); falla
               break;
        
        }   
        
        }
        
    
    }
    
    public static void introducirNom(){
          
        try{
            System.out.println("Introdueix el nom");
		String Nom = sc.next();
            System.out.println("Introdueix el dni");
                String DNI = sc.next();
            System.out.println("Introdueix la data");
                String data = sc.next();
            System.out.println("Introdueix la adreça postal");
                String adreçaPostal = sc.next();
            System.out.println("Introdueix el codi postal");
                int codiPostal = sc.nextInt();
            System.out.println("Sexe home(H) o done(D)");
                String genero = sc.next();
                  
            
        stmt = (Statement) con.createStatement();
	stmt.executeUpdate("INSERT INTO alumnes(Nom,dni,Data_De_Naixement,Adreça_Postal,Sexe,codiPostal)"+ 
                        "VALUES  ('"+Nom+"','"
                                   +DNI+"','"
                                   +data+"','"
                                   +adreçaPostal+"','"
                                   +genero+"','"
                                   +codiPostal+"')");
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Ha fallat l'insercció revisa el codi postal que estigui creat");
          }
           
    }

    public static void esborrarAlumne() throws SQLException {
        System.out.println("Digues el dni del alumne que vols eliminar");
        
        String dni = sc.next();
        
        stmt = (Statement) con.createStatement();
        
        
        res = stmt.executeQuery("SELECT * FROM alumnes WHERE dni = '"+ dni +"'");
        
        //System.out.println(res.next());
     
        if(res.next()==true){
             stmt = (Statement) con.createStatement();
            stmt.execute("DELETE FROM alumnes WHERE dni = '"+ dni +"'");
        }
        
    }

    public static void modificarAlumne() throws SQLException {
        ResultSet resModificacio;
        String Nom;
        String DNI;
        String data;
        String adreçaPostal;
        String genero;
        String codiPostal;
        boolean mod;
        
        stmt = (Statement) con.createStatement();
        
        System.out.println("Digues el dni del alumne que vols modificar");
         DNI = sc.next();
        resModificacio = stmt.executeQuery("SELECT * FROM alumnes WHERE dni ='"+ DNI +"';");
        

        mod=resModificacio.next();
        String n,ap,s,cp;

        if(mod=true){
        
          n=resModificacio.getString(1);
          ap=resModificacio.getString(4);
          s=resModificacio.getString(5);
          cp=resModificacio.getString(6);
          
          
        try{
            
           sc.nextLine();//comprobar si hace falta esta linea
           System.out.println("Nom["+n+"]: ");
           Nom = sc.nextLine();
           if(!(Nom.length()==0)){
            stmt.executeUpdate("UPDATE alumnes SET Nom = '"+ Nom + "'WHERE dni='"+DNI+"';");
           }
           
           System.out.println("Adreça Postal["+ap+"]: ");
           adreçaPostal = sc.nextLine();
           if(!(adreçaPostal.length()==0)){
             stmt.executeUpdate("UPDATE alumnes SET Adreça_Postal = '"+ adreçaPostal + "'WHERE dni='"+DNI+"';");
           }
        
           System.out.println("Sexe["+s+"]: ");
            genero = sc.nextLine();
            if(!(genero.length()==0)){
                 stmt.executeUpdate("UPDATE alumnes SET Sexe = '"+ genero + "'WHERE dni='"+DNI+"';");
            }
           
            System.out.println("Codi Postal["+cp+"]: ");
            codiPostal = sc.nextLine();
            if(!(codiPostal.length()==0)){
            stmt.executeUpdate("UPDATE alumnes SET codiPostal = '"+ codiPostal + "'WHERE dni='"+DNI+"';");
            }
          
            
        }catch(Exception e){
            System.out.println("Fixa't si has posat un codi postal que existeixi");
            e.printStackTrace();
            
        }        
   
            }     

    
    }
    
      public static void afegirPoblacions() throws SQLException {
         try{
         ResultSet resConsul;
         boolean c;
          
        System.out.println("Introdueix un Codi Postal");
            String Codi_Postal = sc.next();
        System.out.println("Introdueix el nom de la població");
            String Nom_poble = sc.next();
            
        stmt = (Statement) con.createStatement();
       
        resConsul = stmt.executeQuery("SELECT Nom_Poblacio FROM poblacions WHERE Nom_Poblacio = '"+ Nom_poble +"'");
        
        c=resConsul.next();
        
        if(c==false){ 
        ResultSet resConculta = stmt.executeQuery("SELECT * FROM alumnes");
        
        stmt = (Statement) con.createStatement();
	stmt.executeUpdate("INSERT INTO poblacions(codiPostal,Nom_Poblacio)"+ 
                        "VALUES  ('"+Codi_Postal+"','"
                                   +Nom_poble+"')");
        }else{
        System.out.println("Error, aquesta població ja existeix en la base de dades");
        }
         }catch(Exception e){
         System.out.println("Error al introduir nova poblacio, fixa't si has posat un codi postal existent");
          e.printStackTrace();
         }
         
        }

    public static void mostrarAlumnes() throws SQLException {
        try{
        stmt = con.prepareStatement("SELECT * FROM alumnes");
    
        ResultSet resConculta = stmt.executeQuery("SELECT * FROM alumnes");
            while(resConculta.next())
            {
                System.out.print("Nom: "+resConculta.getString(1)+"||");    //First Column
                System.out.print("Dni: "+resConculta.getString(2)+"||");    //Second Column
                System.out.print("Data de naixement: "+resConculta.getString(3)+"||");    //Third Column
                System.out.print("Adreça postal: "+resConculta.getString(4)+"||");    //Fourth Column
                System.out.print("Sexe: "+resConculta.getString(5)+"||"); 
                System.out.print("Codi postal: "+resConculta.getString(6)+"||"); 
                System.out.println();
            }
        }catch(Exception e){
            System.out.println("Error");
             e.printStackTrace();
        } 

    }

    public static void mostrarPobles() throws SQLException {
        try{
        stmt = con.prepareStatement("SELECT * FROM poblacions");
    
        ResultSet resConculta = stmt.executeQuery("SELECT * FROM poblacions");
            while(resConculta.next())
            {
                System.out.print("Codi Postal: "+resConculta.getString(1)+"||");    //First Column
                System.out.print("Nom Poblacio: "+resConculta.getString(2)+"||");    //Second Column
                System.out.println();
            }
        }catch(Exception e){
            System.out.println("Error");
             e.printStackTrace();
        }
        
    }

    public static void mostrar1Poble() throws SQLException {
       try{
        String codiPostal;
        System.out.println("Inrodueix el codi postal:");
        codiPostal = sc.next();
       
        stmt = con.prepareStatement("SELECT * FROM poblacions WHERE codiPostal = '"+ codiPostal +"'");
       
            ResultSet resConculta = stmt.executeQuery("SELECT * FROM poblacions WHERE codiPostal = '"+ codiPostal +"'");
            while(resConculta.next())
            {
                System.out.print("Codi Postal: "+resConculta.getString(1)+"||");    //First Column
                System.out.print("Nom Poblacio: "+resConculta.getString(2)+"||");    //Second Column
                System.out.println();
            }

       }catch(Exception e){
           System.out.println("Error");
            e.printStackTrace();
       }

    }

    public static void mostrar1Alumne(){
       try{
        String dni;
        System.out.println("Introdueix el dni del alumne que vols consultar");
        dni = sc.next();
       
        stmt = con.prepareStatement("SELECT * FROM alumnes WHERE dni = '"+ dni +"'");
       
            ResultSet resConculta = stmt.executeQuery("SELECT * FROM alumnes WHERE dni = '"+ dni +"'");
            while(resConculta.next())
            {
                System.out.print("Nom: "+resConculta.getString(1)+"||");    //First Column
                System.out.print("Dni: "+resConculta.getString(2)+"||");    //Second Column
                System.out.print("Data de naixement: "+resConculta.getString(3)+"||");    //Third Column
                System.out.print("Adreça postal: "+resConculta.getString(4)+"||");    //Fourth Column
                System.out.print("Sexe: "+resConculta.getString(5)+"||"); 
                System.out.print("Codi postal: "+resConculta.getString(6)+"||"); 
                System.out.println();
            }

       }catch(Exception e){
         System.out.println("Error");
          e.printStackTrace();
       }
    }

    private static void esborrarPoblacio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
      
}