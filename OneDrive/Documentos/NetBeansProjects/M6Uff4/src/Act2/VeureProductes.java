/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act2;

import ElsMeusBeans.Producte;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/**
 *
 * @author Raul
 */
public class VeureProductes {
    public static void main (String[] args){
    //Obrir la base de dades
        ODB odb = ODBFactory.open("Producte_com.BD");
    //Es recuperen tots els objestes
    Objects<Producte> objects = odb.getObjects(Producte.class);
    System.out.println(objects.size()+" Productes:");
    
    int i = 1;
    //Mostra els productes
    while(objects.hasNext()){
        Producte pro = objects.next();
        System.out.println((i++)+"\t: "+pro.getDescripcio()
          +"*ESTOC ACTUAL:"+pro.getStockactual()+" *PVP:"+pro.getPvp()+
                "*MINIM:"+pro.getStockminim());   
    }
    //Es tanca la base de dades
    odb.close();
    
    }
   
}
