
package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileElaborator fe = new FileElaborator("D:\\Excel utili\\ACCREDITI_50A0.xls", "UTF-16");
        try {
            fe.TESTprintLines(10);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
