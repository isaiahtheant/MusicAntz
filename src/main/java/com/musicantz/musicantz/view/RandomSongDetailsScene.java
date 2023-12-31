package com.musicantz.musicantz.view;

import com.musicantz.musicantz.music.Song;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * The <code>RandomSongDetailsScene</code> class displays the details of a random Song.
 *
 */
public class RandomSongDetailsScene extends Scene {

    public static final int WIDTH = 350;
    public static final int HEIGHT = 200;

    private Button backButton = new Button("Back");

    /**
     * In the <code>RandomSongDetailsScene</code>, a user can view the details of a random Song.
     *
     * @param randomSong The random Song to display details for.
     *
     */
    public RandomSongDetailsScene(Song randomSong) {
        super(new GridPane(), WIDTH, HEIGHT);

        GridPane pane = (GridPane) this.getRoot();
        pane.setAlignment(Pos.CENTER);
        setCommonStyle(pane);

        pane.setHgap(10.0);
        pane.setVgap(5);

        // Display Song details
        Label artistLabel = new Label("Artist: " + randomSong.getArtist());
        Label titleLabel = new Label("Title: " + randomSong.getTitle());
        Label yearLabel = new Label("Year: " + randomSong.getYear());
        Label genreLabel = new Label("Genre: " + randomSong.getGenre());
        Label ratingLabel = new Label("Rating: " + randomSong.getRating());
        Label lengthLabel = new Label("Length: " + formatLength(randomSong.getLength()));

        // Light green text of course
        artistLabel.setStyle("-fx-text-fill: #90EE90;");
        titleLabel.setStyle("-fx-text-fill: #90EE90;");
        yearLabel.setStyle("-fx-text-fill: #90EE90;");
        genreLabel.setStyle("-fx-text-fill: #90EE90;");
        ratingLabel.setStyle("-fx-text-fill: #90EE90;");
        lengthLabel.setStyle("-fx-text-fill: #90EE90;");

        pane.add(artistLabel, 0, 0);
        pane.add(titleLabel, 0, 1);
        pane.add(yearLabel, 0, 2);
        pane.add(genreLabel, 0, 3);
        pane.add(ratingLabel, 0, 4);
        pane.add(lengthLabel, 0, 6);

        pane.add(backButton, 0, 8);
        backButton.setOnAction(e -> goBackToPrevScene());
        this.setRoot(pane);
    }

    private String formatLength(double length) {
        int minutes = (int) length;
        int seconds = (int) ((length - minutes) * 60);
        return String.format("%d:%02d", minutes, seconds);
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("MusicAntz", new MainScene());
    }

    private void setCommonStyle(GridPane pane) {
        // Set Pane to Black / Grey gradient
        pane.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333);");

        // Give Back button linear gradient :)
        backButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
    }
}
