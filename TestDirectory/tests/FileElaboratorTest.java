package tests;

import com.company.lib.FileElaborator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileElaboratorTest {
    @Test
    void getHeader() {
        FileElaborator fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\acc50a0-verifica\\ACCREDITI_50A0.XLS", "UTF-16");
        fe.retriveSapData();
        Assertions.assertEquals(45, fe.getHeader().getHeaderSize());

        fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\rott2\\ROTTAMAZIONE.XLS", "UTF-16");
        fe.retriveSapData();
        Assertions.assertEquals(39, fe.getHeader().getHeaderSize());

        fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\QryEstraiPoMa (3).csv", "UTF-8");
        fe.retriveGridData();
        Assertions.assertEquals(7, fe.getHeader().getHeaderSize());
    }


}