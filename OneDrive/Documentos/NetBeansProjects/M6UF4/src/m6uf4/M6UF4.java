/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6uf4;

import ElsMeusBeans.Comanda;
import ElsMeusBeans.Producte;

/**
 *
 * @author Raul
 */
public class M6UF4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     //int idProducte, String descripcio, int stockActual, int stockMinim, float pvp
    
       
       Producte producte[] = new Producte[3];
       
       producte[0] = new Producte(1, "Portable MSI USB 3.0", 7, 3, 50);
       producte[1] = new Producte(2,"Cubo de Rubik", 5,8,25);
       producte[2] = new Producte(3,"Vela", 10,3,1);
       Comanda comanda = new Comanda();
       
    
       for(int i=0; i<3;i++){
       producte[i].addPropertyChangeListener(comanda);
       //Es canvia l'estoc actual, se li dona valor 2
       }
       
      for(int i=0; i<3;i++){
       producte[i].setStockactual(2);
       //Es canvia l'estoc actual, se li dona valor 2
       }
    
       if(comanda.isDemana()){
       for(int i=0;i<3;i++){
       System.out.println("Fer comanda en producte:" + producte[i].getDescripcio());
       }
       }else{
             for(int i=0;i<3;i++){
           System.out.println("No es necessari fer la comanda en producte: "+ producte[i].getDescripcio());
             }
       }
    
    }
    
}
