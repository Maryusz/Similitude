
package com.company;


import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileElaborator fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\acc50a0-verifica\\ACCREDITI_50A0.XLS", "UTF-16");
        //fe.retriveSapData().forEach(System.out::println);

        fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\rott2\\ROTTAMAZIONE.XLS", "UTF-16");




        //fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\QryEstraiPoMa (3).csv", "UTF-8");
        List<List<String>> l = fe.retriveSapData();
        System.out.println(l.size());
        HeaderManager hm = new HeaderManager(l.get(0));
        //System.out.println(hm.getPosition("﻿QUANTITA"));
        System.out.println(hm);

        HeaderManager hm2 = new HeaderManager(l.get(0));
        System.out.println(hm2);
        System.out.println(l.get(2));
        System.out.println(l.get(3));
        System.out.println(l.get(2));





    }
}
