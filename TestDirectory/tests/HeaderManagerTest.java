package tests;

import com.company.lib.FileElaborator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HeaderManagerTest {
   private static FileElaborator fe;
    @BeforeAll
    static void initializeClass(){
        fe = new FileElaborator("C:\\Users\\Mariusz\\Google Drive\\Lavoro\\Progetti Griglia\\xls sap - griglia\\acc50a0-verifica\\ACCREDITI_50A0.XLS", "UTF-16");

    }
    @Test
    void getPosition() {
        fe.retriveSapData();
        Assertions.assertEquals(3, fe.getHeader().getPosition("Return Vendor"));
    }

    @Test
    void getColumnName() {
        Assertions.assertEquals("quantità", fe.getHeader().getColumnName(13));

    }

    @Test
    void getHeaderSize() {
        Assertions.assertEquals(45, fe.getHeader().getHeaderSize());
    }

}