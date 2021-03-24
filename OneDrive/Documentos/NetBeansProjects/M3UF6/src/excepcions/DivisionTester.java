/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcions;

import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class DivisionTester {
    
    static Scanner scan = new Scanner (System.in);
    static int resultado;
    static boolean sigue=true;
    static int numerator;
    static int divisor;
    static String number;
    static String numDivisor;

public static void main (String[] args) {
    

    while(sigue==true){
        try{
        System.out.println("Enter the numerator:");
        number = scan.nextLine();        
        
        if(number.equalsIgnoreCase("q")){
        sigue=false;
        }else{
        introdueix();
        }

        }catch(Exception e){
        System.out.println ("You entered bad data");
        System.out.println ("Please try again");
        
        }
        }

}


    private static void dividir(int numerator, int divisor) {
        try{
        resultado = numerator / divisor;
        System.out.println(numerator+" / "+divisor+" is "+resultado);
           }catch(ArithmeticException e){
        System.out.println ("division by zero or poor input data"+e);
        }
    }

    private static void introdueix() {
        numerator = Integer.parseInt(number);
        System.out.println("Enter the divisor:");
        numDivisor = scan.nextLine();
        divisor = Integer.parseInt(numDivisor);
        dividir(numerator,divisor);
       
    }

  
}





    

