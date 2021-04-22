/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6uff4;

import ElsMeusBeans.Comanda;
import ElsMeusBeans.Producte;

/**
 *
 * @author Raul
 */
public class M6Uff4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Producte producte = new Producte(1, "Portable MSI USB 3.0", 10,3,50);
       
       Producte producte[] = new Producte[3];
       Comanda comanda = new Comanda();
       
       producte[0] = new Producte(1, "Portable MSI USB 3.0", 10,3,50);
       producte[1] = new Producte(2, "Asus PRIME B550M-K",6,6,85);
       producte[2] = new Producte(3, "Asus GeForce GT 1030 2GB GDDR5",7,7,70);
       
       for(int i = 0; i<8;i++){
       producte[i].addPropertyChangeListener(comanda);
       producte[i].setStockactual(2);
        if(comanda.isDemana()){
            System.out.println("Fer comanda en producte: "+producte[i].getDescripcio());          
        }else{
            System.out.println("No és necessari fer la comanda en producte: "+producte[i].getDescripcio());
        }
       }
      
    }
    
}
