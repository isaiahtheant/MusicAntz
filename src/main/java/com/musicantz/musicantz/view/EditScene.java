// EditScene.java
package com.musicantz.musicantz.view;

import com.musicantz.musicantz.controller.Controller;
import com.musicantz.musicantz.music.Song;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class EditScene extends Scene {
    private Button saveButton = new Button("Save");  // Declare buttons here
    private Button cancelButton = new Button("Cancel");

    private void setCommonStyle(GridPane pane){

        // Set Pane to Black / Grey gradient
        pane.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333);");

        // Set Song text fields to the gradient / change text inside to Light Green
        artistTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        genreTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        titleTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        yearTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        ratingTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");


        // Give Save / Cancel Black / Grey gradient :)
        saveButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        cancelButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
    }
    private Song selectedSong;

    /**
     * Text Fields to enter specific Song Info
     *
     */
    private TextField artistTF = new TextField();
    private TextField titleTF = new TextField();

    private TextField yearTF = new TextField();
    private TextField genreTF = new TextField();

    private TextField ratingTF = new TextField();


    /**
     * Error Labels
     *
     */
    private Label artistErrorLabel = new Label("Artist is required.");
    private Label titleErrorLabel = new Label("Title is required.");
    private Label yearErrorLabel = new Label("Year is required.");
    private Label genreErrorLabel = new Label("Genre is required.");
    private Label ratingErrorLabel = new Label("Rating is required.");





    public EditScene(Song selectedSong) {
        super(new GridPane(), 750, 280);

        this.selectedSong = selectedSong;
        GridPane gridPane = (GridPane) this.getRoot();
        setCommonStyle(gridPane);
        gridPane.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333);");
        // Give Save / Cancel linear gradient :)


        /**
         * Add TextFormatter to restrict the number of characters in Minutes / Seconds / Rating
         *
         */
        TextFormatter<?> yearFormatter = new TextFormatter<>((Change c) -> {
            if (c.getControlNewText().matches("\\d{0,4}")) {
                return c;
            } else {
                return null;
            }
        });

        TextFormatter<?> ratingFormatter = new TextFormatter<>((Change c) -> {
            if (c.getControlNewText().matches("\\d{0,2}")) {
                return c;
            } else {
                return null;
            }
        });

        TextFormatter<?> minutesFormatter = new TextFormatter<>((Change c) -> {
            if (c.getControlNewText().matches("\\d{0,3}")) {
                return c;
            } else {
                return null;
            }
        });

        TextFormatter<?> secondsFormatter = new TextFormatter<>((Change c) -> {
            if (c.getControlNewText().matches("\\d{0,2}")) {
                return c;
            } else {
                return null;
            }
        });

        // Set up text formatter for these fields
        yearTF.setTextFormatter(yearFormatter);
        ratingTF.setTextFormatter(ratingFormatter);


        /**
         * Initialize text fields with existing song details
         *
         */
        artistTF.setText(selectedSong.getArtist());
        titleTF.setText(selectedSong.getTitle());

        yearTF.setText(String.valueOf(selectedSong.getYear()));
        genreTF.setText(selectedSong.getGenre());

        ratingTF.setText(String.valueOf(selectedSong.getRating()));

        double length = selectedSong.getLength();
        int minutes = (int) length;
        int seconds = (int) ((length - minutes) * 60);



        // New Grid Pane with Song info
        GridPane pane = new GridPane();
        gridPane.setHgap(10.0);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(5));


        gridPane.add(new Label("Artist:"), 0, 0);
        gridPane.add(artistTF, 1, 0);
        gridPane.add(artistErrorLabel, 2, 0);
        artistErrorLabel.setTextFill(Color.RED);
        artistErrorLabel.setVisible(false);

        // Green text Artist label
        Label artistLabel = new Label("Artist:");
        artistLabel.setTextFill(Color.web("#90EE90"));
        gridPane.add(artistLabel, 0, 0);


        gridPane.add(new Label("Title:"), 0, 1);
        gridPane.add(titleTF, 1, 1);
        gridPane.add(titleErrorLabel, 2, 1);
        titleErrorLabel.setTextFill(Color.RED);
        titleErrorLabel.setVisible(false);

        // Green text Title label
        Label titleLabel = new Label("Title:");
        titleLabel.setTextFill(Color.web("#90EE90"));
        gridPane.add(titleLabel, 0, 1);


        gridPane.add(new Label("Year:"), 0, 2);
        gridPane.add(yearTF, 1, 2);
        gridPane.add(yearErrorLabel, 2, 2);
        yearErrorLabel.setTextFill(Color.RED);
        yearErrorLabel.setVisible(false);

        // Green text Year label
        Label yearLabel = new Label("Year:");
        yearLabel.setTextFill(Color.web("#90EE90"));
        gridPane.add(yearLabel, 0, 2);


        gridPane.add(new Label("Genre:"), 0, 3);
        gridPane.add(genreTF, 1, 3);
        gridPane.add(genreErrorLabel, 2, 3);
        genreErrorLabel.setTextFill(Color.RED);
        genreErrorLabel.setVisible(false);

        // Green text Genre label
        Label genreLabel = new Label("Genre:");
        genreLabel.setTextFill(Color.web("#90EE90"));
        gridPane.add(genreLabel, 0, 3);


        gridPane.add(new Label("Rating:"), 0, 4);
        gridPane.add(ratingTF, 1, 4);
        gridPane.add(ratingErrorLabel, 2, 4);
        ratingErrorLabel.setTextFill(Color.RED);
        ratingErrorLabel.setVisible(false);

        // Green text Rating label
        Label ratingLabel = new Label("Rating [1-10]:");
        ratingLabel.setTextFill(Color.web("#90EE90"));
        gridPane.add(ratingLabel, 0, 4);


        saveButton.setOnAction(e -> saveChanges());
        gridPane.add(saveButton, 0, 5);


        gridPane.add(cancelButton, 0, 6);
        cancelButton.setOnAction(e -> goBackToPrevScene());

        this.setRoot(gridPane);
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("MusicAntz", new MainScene());
    }

    private void saveChanges() {
        // Get modified details from the text fields
        String newArtist = artistTF.getText();
        String newTitle = titleTF.getText();
        String newGenre = genreTF.getText();
        String newRatingText = ratingTF.getText();
        String newYearText = yearTF.getText();


        /**
         * Validate that all fields are not empty
         *
         */
        if (newArtist.isEmpty() || newTitle.isEmpty() || newGenre.isEmpty() ||
                newRatingText.isEmpty() || newYearText.isEmpty()) {

            artistErrorLabel.setVisible(newArtist.isEmpty());
            titleErrorLabel.setVisible(newTitle.isEmpty());
            genreErrorLabel.setVisible(newGenre.isEmpty());
            ratingErrorLabel.setVisible(newRatingText.isEmpty());
            yearErrorLabel.setVisible(newYearText.isEmpty());
            return;
        }

        try {
            int newYear = Integer.parseInt(newYearText);
            int newRating = Integer.parseInt(newRatingText);


            // Validate year
            yearErrorLabel.setVisible(newYear < 1920 || newYear > 2025);

            // Validate numeric fields
            ratingErrorLabel.setVisible(newRating < 1 || newRating > 10);


            // If any of the error labels are visible, return.
            if (yearErrorLabel.isVisible() || ratingErrorLabel.isVisible()) {
                return;
            }

            // Update the selectedSong with the modified details
            selectedSong.setArtist(newArtist);
            selectedSong.setTitle(newTitle);
            selectedSong.setYear(newYear);
            selectedSong.setGenre(newGenre);
            selectedSong.setRating(newRating);

            // Call the Controller method to save changes
            Controller.getInstance().saveData();

            // Close the EditScene / go back to the previous scene
            goBackToPrevScene();

        } catch (NumberFormatException e) {

            // Show a warning message for invalid numeric input
            System.out.println("Please enter only valid number values");
        }


    }
}
