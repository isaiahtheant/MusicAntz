package com.musicantz.musicantz.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * The <code> Music </code> class represents the business logic (data and calculations) of the application.
 * In this app, it either loads burrito from a CSV file (first load) or a binary file (all
 * subsequent loads).  It is also responsible for saving data to a binary file.
 *
 */
public class Music {
    public static final String BINARY_FILE = "AllSongs.dat";

    /**
     * Determines whether the binary file exists and has data (size/length > 5L bytes).
     * @return True if the binary file exists and has data, false otherwise.
     *
     */
    public static boolean binaryFileHasData()
    {
        File binaryFile = new File(BINARY_FILE);
        return (binaryFile.exists() && binaryFile.length() > 5L);
    }

    /**
     * Populates the list of all songs from the binary file. This will be called everytime the application loads,
     * @return The list of all songs populated from the binary file
     *
     */
    public static ObservableList<com.musicantz.musicantz.music.Song> populateListFromBinaryFile() {

        ObservableList<com.musicantz.musicantz.music.Song> allSongs = FXCollections.observableArrayList();
        File binaryFile = new File(BINARY_FILE);
        if (binaryFileHasData()) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                com.musicantz.musicantz.music.Song[] tempArray = (com.musicantz.musicantz.music.Song[]) fileReader.readObject();
                allSongs.addAll(tempArray);
                fileReader.close();
            } catch (Exception e) {
                System.err.println("Error opening file: " + BINARY_FILE + " for reading.\nCaused by: " + e.getMessage());
            }
        }
        return allSongs;
    }

    /**
     * Saves the list of all songs to the binary file. This will be called each time the application stops,
     * which occurs when the user exits/closes the app.  Note this method is called in the View, by the controller,
     * during the stop() method.
     * @return True if the data were saved to the binary file successfully, false otherwise.
     *
     */
    public static boolean writeDataToBinaryFile(ObservableList<com.musicantz.musicantz.music.Song> allSongs)
    {
        // File reference
        File binaryFile = new File(BINARY_FILE);
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            com.musicantz.musicantz.music.Song[] tempArray = new com.musicantz.musicantz.music.Song[allSongs.size()];
            allSongs.toArray(tempArray);
            fileWriter.writeObject(tempArray);
            fileWriter.close();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error writing binary file: " + BINARY_FILE + "\n" + e.getMessage());
            return false;
        }
    }

}
