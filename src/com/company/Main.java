
package com.company;


import com.company.lib.FileElaborator;
import com.company.lib.ObjectFactory;

public class Main {

    public static void main(String[] args) {
        FileElaborator fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\acc50a0-verifica\\ACCREDITI_50A0.XLS", "UTF-16");
        //fe.retriveSapData();
        //fe.printIndexedObject(3);



        fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\rott2\\ROTTAMAZIONE.XLS", "UTF-16");

        //fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\QryEstraiPoMa (3).csv", "UTF-8");
        fe.retriveSapData();
       // System.out.println(fe.getHeader());
        fe.printIndexedObject(3);


        //fe.getData().stream()
          //      .forEach(System.out::println);




    }
}
