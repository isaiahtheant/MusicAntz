package com.musicantz.musicantz.music;

import java.io.Serializable;
import java.util.Objects;

/**
 * The <code>Song</code> class represents a song, with information such as its artist, title, year,
 * genre, rating, length
 *
 */

// Makes sure class is Serializable and implements Comparable
public class Song implements Serializable, Comparable<Song> {
    private String artist;
    private String title;

    private int year;
    private String genre;
    private int rating;
    private double length;
    private String formattedLength;

    public Song(String artist, String title, int year, String genre, int rating, double length, String formattedLength) {
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.length = length;
        this.formattedLength = formattedLength;

    }

    /** Getters and Setters
     *
     */
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getFormattedLength() {
        return formattedLength;
    }

    public void setFormattedLength(String formattedLength) {
        this.formattedLength = formattedLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Double.compare(song.length, length) == 0 &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(title, song.title) &&
                Objects.equals(year, song.year) &&
                Objects.equals(genre, song.genre) &&
                rating == song.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title, year, genre, rating, length);
    }

    /**
     * toString which returns a string summary of the Song.
     * Format includes Song parameters artist, title, year, genre, rating, and length.
     *
     * @return String representation of the Song.
     *
     */
    @Override
    public String toString() {
        return "Song [" +
                "Artist: " + artist +
                " | Title: " + title +
                " | Year: " + year +
                " | Genre: " + genre +
                " | Rating: " + rating + " stars" +
                " | Length: " + formattedLength +
                ']';
    }

    /**
     * Compares this Song with another based on year, genre, and artist.
     * Used for sorting Songs in ascending order.
     *
     * @param other The Song to compare with.
     * @return Negative, zero, or positive if this Song is less than, equal to, or greater than the other Song.
     *
     */
    @Override
    public int compareTo(Song other) {
        // Sort by year genre etc//
        int yearComp = this.year - other.year;
        if (yearComp != 0) return yearComp;

        int genreComp = this.genre.compareTo(other.genre);
        if (genreComp != 0) return genreComp;


        return this.artist.compareTo(other.artist);
    }
}
