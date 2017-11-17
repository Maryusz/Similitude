package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class take as argument a Grid file (csv) or a SAP file (txt / cls) and its purpose is to
 * return polished data.
 */
public class FileElaborator implements IFileElaborator {

    private Logger LOGGER = Logger.getLogger(getClass().getName());
    private File file;
    private BufferedReader bufferedReader;

    /**
     * Receive the path of the file you want to create objects from, and the encoding of the file.
     * @param path Path of the formatted file you want to create objects from
     * @param encoding Encoding of the file passed
     */
    public FileElaborator(String path, String encoding) {

        LOGGER.info("Initializing class");

        // Creates a File object from the path String
        file = new File(path);

        // Istantiate the Buffered reader object with encoding passed to InputStreamReader.
        try {
             bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
             LOGGER.info("Buffered reader instanitated.");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "IOException inside constructor");
        }
    }

    /**
     * Method only for test, don't USE
     * @param number number of readed lines
     * @throws IOException
     */
    @Deprecated
    public void TESTprintLines(int number) throws IOException {
        // TODO: ONLY FOR TESTS
        for (int i = 0; i< number; i++){
            System.out.println(polishAndSplit(bufferedReader.readLine(), "\t"));
        }
    }

    /**
     * Return a polished (all upperCase and trimmed) list of the line passed, using the separator to split.
     * @param line Line of text to split
     * @param separator Separator used in the file to separate values.
     * @return List of string all upper case and trimmed.
     */
    private List<String> polishAndSplit(String line, String separator){

        List<String> listFromLine = Arrays.asList(line.split(separator));

        listFromLine.stream()
                .map(String::toUpperCase)
                .map(String::trim);

        return listFromLine;

    }


    @Override
    public List<List<String>> retriveGridData() {
        LOGGER.info("Retriving polished GRID data...");
        return bufferedReader.lines()
                .map(line -> line.replace("\"", ""))
                .map(line -> polishAndSplit(line, ";"))
                .collect(Collectors.toList());
    }

    @Override
    public List<List<String>> retriveSapData() {
        LOGGER.info("Retriving polished SAP data...");
        return bufferedReader.lines()
                .map(line -> polishAndSplit(line, "\t"))
                .filter(line -> line.size() > 1)
                .collect(Collectors.toList());
    }
}
