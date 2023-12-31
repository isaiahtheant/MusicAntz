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

/**
 * The <code>AddScene</code> class allows a user to enter the information to add a new Song to the collection
 * of the MusicAntz data.
 * Fields are validated (must be provided) before the user is able to save the information.
 *
 */
public class AddScene extends Scene {


    private Scene previousScene;

    public static final int WIDTH = 760;
    public static final int HEIGHT = 280;

    private Controller controller = Controller.getInstance();

    private TextField artistTF = new TextField();
    private Label artistErrorLabel = new Label("Artist is required.");

    private TextField titleTF = new TextField();
    private Label titleErrorLabel = new Label("Title is required.");

    private TextField yearTF = new TextField();
    private Label yearErrorLabel = new Label("Year is required.");

    private TextField genreTF = new TextField();
    private Label genreErrorLabel = new Label("Genre is required.");

    private TextField ratingTF = new TextField();
    private Label ratingErrorLabel = new Label("Rating is required.");

    private TextField minutesTF = new TextField();
    private Label minutesErrorLabel = new Label("Minutes is required.");

    private TextField secondsTF = new TextField();
    private Label secondsErrorLabel = new Label("Seconds is required.");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    /**
     * In the <code>AddScene</code>, a user is able to add a new Song to the collection of current Songs.
     * All fields are required (error message will appear if not provided)
     * @param previousScene A reference to the MainScene so that after saving (or canceling), user
     * is returned back to the main scene.
     */
    public AddScene(Scene previousScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        GridPane pane = (GridPane) this.getRoot();
        setCommonStyle(pane);


        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Artist:"), 0, 0);
        pane.add(artistTF, 1, 0);
        pane.add(artistErrorLabel, 2, 0);
        artistErrorLabel.setTextFill(Color.RED);
        artistErrorLabel.setVisible(false);

        // Green text Artist label
        Label artistLabel = new Label("Artist:");
        artistLabel.setTextFill(Color.web("#90EE90"));
        pane.add(artistLabel, 0, 0);

        pane.add(new Label("Title:"), 0, 1);
        pane.add(titleTF, 1, 1);
        pane.add(titleErrorLabel, 2, 1);
        titleErrorLabel.setTextFill(Color.RED);
        titleErrorLabel.setVisible(false);

        // Green text Artist label
        Label titleLabel = new Label("Title:");
        titleLabel.setTextFill(Color.web("#90EE90"));
        pane.add(titleLabel, 0, 1);

        pane.add(new Label("Year:"), 0, 2);
        pane.add(yearTF, 1, 2);
        pane.add(yearErrorLabel, 2, 2);
        yearErrorLabel.setTextFill(Color.RED);
        yearErrorLabel.setVisible(false);

        // Green text Year label
        Label yearLabel = new Label("Year:");
        yearLabel.setTextFill(Color.web("#90EE90"));
        pane.add(yearLabel, 0, 2);


        pane.add(new Label("Genre:"), 0, 3);
        pane.add(genreTF, 1, 3);
        pane.add(genreErrorLabel, 2, 3);
        genreErrorLabel.setTextFill(Color.RED);
        genreErrorLabel.setVisible(false);

        // Green text Genre label
        Label genreLabel = new Label("Genre:");
        genreLabel.setTextFill(Color.web("#90EE90"));
        pane.add(genreLabel, 0, 3);


        pane.add(new Label("Rating [1-10]:"), 0, 4);
        pane.add(ratingTF, 1, 4);
        pane.add(ratingErrorLabel, 2, 4);
        ratingErrorLabel.setTextFill(Color.RED);
        ratingErrorLabel.setVisible(false);

        // Green text Rating label
        Label ratingLabel = new Label("Rating [1-10]:");
        ratingLabel.setTextFill(Color.web("#90EE90"));
        pane.add(ratingLabel, 0, 4);

        pane.add(new Label("Length:"), 0, 6);

        pane.add(minutesTF, 1, 6);
        pane.add(secondsTF, 2, 6);
        pane.add(minutesErrorLabel, 4, 6);
        pane.add(secondsErrorLabel, 5, 6);

        minutesErrorLabel.setTextFill(Color.RED);
        secondsErrorLabel.setTextFill(Color.RED);
        minutesErrorLabel.setVisible(false);
        secondsErrorLabel.setVisible(false);


        // Green text Length label
        Label lengthLabel = new Label("Length:");
        lengthLabel.setTextFill(Color.web("#90EE90"));
        pane.add(lengthLabel, 0, 6);


        /**
         * Add TextFormatter to restrict the number of characters in Minutes / Seconds / Rating / Year
         *
         */
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

        TextFormatter<?> ratingFormatter = new TextFormatter<>((Change c) -> {
            if (c.getControlNewText().matches("\\d{0,2}")) {
                return c;
            } else {
                return null;
            }
        });

        TextFormatter<?> yearFormatter = new TextFormatter<>((Change c) -> {
            if (c.getControlNewText().matches("\\d{0,4}")) {
                return c;
            } else {
                return null;
            }
        });

        // Set text formatter for these fields
        ratingTF.setTextFormatter(ratingFormatter);
        minutesTF.setTextFormatter(minutesFormatter);
        secondsTF.setTextFormatter(secondsFormatter);
        yearTF.setTextFormatter(yearFormatter);

        pane.add(cancelButton, 0, 8);
        pane.add(saveButton, 0, 7);

        saveButton.setOnAction(e -> save());
        cancelButton.setOnAction(e -> goBackToPrevScene());
        this.setRoot(pane);
    }

    /**
     * Reads each of the text fields and validates them.  If the required fields
     * are empty, user will see an error message (red text) next to the text field and will be prevented from saving.
     * Otherwise, a new Song object will be instantiated and added to the list of all Songs.
     *
     */
    private void save() {
        int year = 0, rating = 0;
        String artist, title, genre;
        double length = 0.0;

        try {
            year = Integer.parseInt(yearTF.getText());
            // Assume songs can be released after the invention of recorded music (late 19th century)
            // Also don't want users to accidentally submit a song in a year we haven't reached
            yearErrorLabel.setVisible(year < 1919 || year > 2025);
        } catch (NumberFormatException e) {
            yearErrorLabel.setVisible(true);
        }

        artist = artistTF.getText();
        artistErrorLabel.setVisible(artist.isEmpty());

        title = titleTF.getText();
        titleErrorLabel.setVisible(title.isEmpty());

        genre = genreTF.getText();
        genreErrorLabel.setVisible(genre.isEmpty());

        try {
            rating = Integer.parseInt(ratingTF.getText());
            // Music Ratings between [1-10]
            ratingErrorLabel.setVisible(rating < 1 || rating > 10);
        } catch (NumberFormatException e) {
            ratingErrorLabel.setVisible(true);
        }

        try {
            int minutes = Integer.parseInt(minutesTF.getText());
            // Validate minutes
            minutesErrorLabel.setVisible(minutes < 0);
        } catch (NumberFormatException e) {
            minutesErrorLabel.setVisible(true);
        }

        try {
            int seconds = Integer.parseInt(secondsTF.getText());
            // Validate seconds
            secondsErrorLabel.setVisible(seconds < 0 || seconds >= 60);
        } catch (NumberFormatException e) {
            secondsErrorLabel.setVisible(true);
        }

        // If any of the error labels are visible, return.
        if (yearErrorLabel.isVisible() || artistErrorLabel.isVisible() || titleErrorLabel.isVisible() ||
                genreErrorLabel.isVisible() || ratingErrorLabel.isVisible() || minutesErrorLabel.isVisible() || secondsErrorLabel.isVisible()) {
            return;
        }

        // Calculate the length in the same way as the random song details
        int minutes = Integer.parseInt(minutesTF.getText());
        int seconds = Integer.parseInt(secondsTF.getText());

       double songLength = minutes + seconds / 60.0;

        String formattedLength = formatLength(songLength);


        controller.addSong(new Song(artist, title, year, genre, rating, songLength, formattedLength));

        goBackToPrevScene();
    }


    /**
     * Navigates back to the previous scene without having to create a new one.
     *
     */
    private void goBackToPrevScene() {
        ViewNavigator.loadScene("MusicAntz", previousScene);
    }



    private String formatLength(double length) {
        int minutes = (int) length;
        int seconds = (int) ((length - minutes) * 60);
        return String.format("%d:%02d", minutes, seconds);
    }



    private void setCommonStyle(GridPane pane) {

        // Set Pane to Black / Grey gradient
        pane.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333);");

        // Set Song text fields to the gradient / change text inside to Light Green
        artistTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        genreTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        titleTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        yearTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        ratingTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        minutesTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        secondsTF.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");

        // Give Save / Cancel linear gradient :)
        saveButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        cancelButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
    }
}
