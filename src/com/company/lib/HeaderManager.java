package com.company.lib;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represent the header of the file you pass, its only purpose its to simplify the getting of indexes and names
 * of the columns.
 *
 * @version 0.1
 * @author Mariusz A. Bilda
 */

public class HeaderManager {
    private List<String> headerFromFile;

    public HeaderManager(List<String> headerFromFile) {
        // From the passed header, this stream polish and trim the data of the header.
        this.headerFromFile = headerFromFile.stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Return the index of a passed column name, if the column name is not present it throws an
     * IllegalArgumentException (case insensitive).
     * @param requiredPosition The column name you need position of.
     * @return Column index
     */
    public int getPosition(String requiredPosition) {
        if (headerFromFile.contains(requiredPosition.toLowerCase())){
            return headerFromFile.indexOf(requiredPosition.toLowerCase());
        } else {
            throw new IllegalArgumentException(String.format("%s doesn't exist in the header", requiredPosition));
        }
    }

    /**
     * This method simply returns the name of the index you pass,
     * can throw IndexOutOfBoundsException if index < 0 or index > header.size()
     * @param columnIndex Index you want the name of.
     * @return Name of the column
     */
    public String getColumnName(int columnIndex) {
        return headerFromFile.get(columnIndex);
    }

    /**
     * Returns the headers size.
     * @return the header size.
     */
    public int getHeaderSize(){
        return headerFromFile.size();
    }

    @Override
    public String toString() {
        return "HeaderManager{" +
                "headerFromFile=" + headerFromFile + ", " +
                "size=" + getHeaderSize() +
                '}';
    }
}
