
package com.musicantz.musicantz.controller;

import com.musicantz.musicantz.music.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * The <code>Controller</code> is a Singleton object that relays all commands between the Music and View
 * (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
 * method.
 *
 */
public class Controller {

    private static Controller theInstance;
    private ObservableList<com.musicantz.musicantz.music.Song> allSongs;
    private ObservableList<com.musicantz.musicantz.music.Song> filteredSongs;
    private ObservableList<String> distinctGenres;
    private ObservableList<String> distinctArtists;

    private Controller() {
        allSongs = com.musicantz.musicantz.music.Music.populateListFromBinaryFile();
        distinctGenres = initializeDistinctGenres();
        distinctArtists = initializeDistinctArtists();
        filteredSongs = FXCollections.observableArrayList();
    }

    /**
     * Gets the one instance of the Controller.
     *
     * @return The instance
     */
    public static Controller getInstance() {
        if (theInstance == null) {
            theInstance = new Controller();

            if (com.musicantz.musicantz.music.Music.binaryFileHasData())
                theInstance.allSongs = com.musicantz.musicantz.music.Music.populateListFromBinaryFile();

            theInstance.distinctGenres = theInstance.initializeDistinctGenres();
            theInstance.filteredSongs = FXCollections.observableArrayList();
        }
        return theInstance;
    }

    /**
     * Gets the list of all songs.
     *
     * @return The list of all songs.
     *
     */
    public ObservableList<Song> getAllSongs() {
        return allSongs;
    }

    public void addSong(Song s) {
        allSongs.add(s);
        if (!distinctGenres.contains(s.getGenre())) {
            distinctGenres.add(s.getGenre());
        }
        Collections.sort(allSongs);
        Collections.sort(distinctGenres);

        saveData();
    }

    public void removeSong(Song s) {
        allSongs.remove(s);
        saveData();
    }

    /**
     * Makes a request for the model to save all song data to
     * a persistent binary file.
     */
    public void saveData() {
        com.musicantz.musicantz.music.Music.writeDataToBinaryFile(allSongs);
    }


    /**
     * Gets a list of the distinct song Genres / Artists.
     *
     * @return The list of the distinct song genres.
     */
    public ObservableList<String> getDistinctGenres() {
        return distinctGenres;
    }

    public ObservableList<String> getDistinctArtists() {
        return distinctArtists;
    }

    /**
     * Initializes the list of distinct Genres / Artists with empty as the first value.
     *
     * @return The list of distinct Genres / Artists with empty as the first value.
     *
     */
    private ObservableList<String> initializeDistinctGenres() {
        ObservableList<String> distinctGenres = FXCollections.observableArrayList();
        distinctGenres.add(""); // Empty entry to the list (blank), so user has option to see all makes
        for (Song s : allSongs) {
            if (!distinctGenres.contains(s.getGenre())) {
                distinctGenres.add(s.getGenre());
            }
        }
        Collections.sort(distinctGenres);
        return distinctGenres;
    }

    private ObservableList<String> initializeDistinctArtists() {
        ObservableList<String> distinctArtists = FXCollections.observableArrayList();
        distinctArtists.add(""); // Add an empty entry for no artist filter
        for (Song s : allSongs) {
            if (!distinctArtists.contains(s.getArtist())) {
                distinctArtists.add(s.getArtist());
            }
        }
        Collections.sort(distinctArtists);
        return distinctArtists;
    }

    public ObservableList<Song> filter(String genre, int year, String artist, boolean isYearFilterEnabled ) {
        filteredSongs.clear(); // Clear the filtered songs list

        for (Song s : allSongs) {
            boolean genreMatches = genre == null || genre.isEmpty() || s.getGenre().equalsIgnoreCase(genre);
            boolean yearMatches = !isYearFilterEnabled || s.getYear() == year;
            boolean artistMatches = artist == null || artist.isEmpty() || s.getArtist().equalsIgnoreCase(artist);

            if (genreMatches && yearMatches && artistMatches) {
                filteredSongs.add(s);
            }
        }

        return filteredSongs;
    }

}