package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class take as argument a Grid file (csv) or a SAP file (txt / cls) and its purpose is to
 * return polished data.
 */
public class FileElaborator implements IFileElaborator {

    private Logger LOGGER = Logger.getLogger(getClass().getName());
    private File file;
    private BufferedReader bufferedReader;
    private List<String> readedLines;

    /**
     * Receive the path of the file you want to create objects from, and the encoding of the file.
     * @param path Path of the formatted file you want to create objects from
     * @param encoding Encoding of the file passed
     */
    public FileElaborator(String path, String encoding) {

        LOGGER.info("Initializing class");

        // Creates a File object from the path String
        file = new File(path);
        readedLines = new ArrayList<>();
        istantiateFile(encoding);
    }

    private void istantiateFile(String encoding){

        // Istantiate the Buffered reader object with encoding passed to InputStreamReader.
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            LOGGER.info("Buffered reader instanitated.");
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                readedLines.add(line);
            }

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
    public void TESTprintLines(int number) {
        for (String s: readedLines){
            System.out.println(s);
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


    /**
     * Creade List<List<String>> of GRID data.
     * @return list of list of strings.
     */
    @Override
    public List<List<String>> retriveGridData() {
        LOGGER.info("Retriving polished GRID data...");
        List<List<String>> l = readedLines.stream()
                .map(line -> line.replace("\"", ""))
                .map(line -> polishAndSplit(line, ";"))
                .collect(Collectors.toList());

        return l;

    }

    /**
     * Creade List<List<String>> of SAP data.
     * @return list of list of strings.
     */
    @Override
    public List<List<String>> retriveSapData() {
        LOGGER.info("Retriving polished SAP data...");

        List<List<String>> l = readedLines.stream()
                .map(line -> polishAndSplit(line, "\t"))
                .filter(line -> line.size() > 3)
                .collect(Collectors.toList());

        return l;
    }

}
