package com.company.lib;

import java.io.*;
import java.util.*;
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
    private List<String> readedLines;
    private HeaderManager mHeaderManager;
    private List<List<String>> data;

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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            LOGGER.info("[0] - Buffered reader instantiated.");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readedLines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "[x] - IOException inside constructor");
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
     */
    @Override
    public void retriveGridData() {
        LOGGER.info("Retriving polished GRID data...");

        data = readedLines.stream()
                .map(line -> line.replace("\"", ""))
                .map(line -> polishAndSplit(line, ";"))
                .collect(Collectors.toList());

        mHeaderManager = new HeaderManager(data.get(0));

    }

    /**
     * Creade List<List<String>> of SAP data.
     */
    @Override
    public void retriveSapData() {
        LOGGER.info("Retriving polished SAP data...");

        data = readedLines.stream()
                .map(line -> polishAndSplit(line, "\t"))
                .filter(line -> line.size() > 3)
                .collect(Collectors.toList());
        mHeaderManager = new HeaderManager(data.get(0));
    }

    /**
     * Creates an instance of header manager, it permits to simpler define lines and indexes of the passed file.
     * @return HeaderManager object
     */
    public HeaderManager getHeader(){
        return mHeaderManager;
    }

    /**
     * Returns the formatted data from the file without header line (0).
     * @return
     */
    public List<List<String>> getData() {
        data.remove(0);
        return data;
    }

    public void printIndexedObject(int position){
        String format = "%-6s %-30s [%s]%n";
        System.out.format(format, "Index", "Name", "Value");
        int count = 0;

        Iterator<String> iterator = getData().get(position).iterator();
        while (iterator.hasNext()) {
            System.out.format(format, count, mHeaderManager.getColumnName(count), iterator.next());
            count++;
        }
    }
}
