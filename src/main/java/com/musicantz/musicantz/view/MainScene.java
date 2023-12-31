package com.musicantz.musicantz.view;

import com.musicantz.musicantz.controller.Controller;
import com.musicantz.musicantz.music.Song;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * The <code>MainScene</code> represents the very first scene for the MusicAntz app.
 * It contains filters for the genre and year.
 * The <code>MainScene</code> also allows for a user to add a new song or remove an existing one.
 */
public class MainScene extends Scene {

    public static final int WIDTH = 1050;
    public static final int HEIGHT = 700;

    private ImageView songsIV = new ImageView();

    //private ComboBox<String> genresCB;
    //private ComboBox<String> artistsCB;

    private TextField genreTextField;
    private TextField artistTextField;

    private Slider yearSlider = new Slider(1920.00, 2024.0, 1.0);
    private ToggleButton yearToggle = new ToggleButton("Toggle Year");

    private ListView<Song> songsLV = new ListView<>();
    private Button addButton = new Button("+ Add Song");
    private Button editButton = new Button("! Edit Song");
    private Button removeButton = new Button("- Remove Song");

    private Button randomSongButton = new Button("* Choose Random Song");

    private Controller controller = Controller.getInstance();
    private ObservableList<Song> songsList;
    private Song selectedSong;

    private void setComboBoxStyle(ComboBox<String> comboBox) {
        comboBox.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        comboBox.setOnShowing(e -> comboBox.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;"));
        comboBox.setOnHiding(e -> comboBox.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;"));


        songsLV.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Song item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                    // Set the text fill and background color
                    setStyle("-fx-text-fill: #90EE90; -fx-background-color: linear-gradient(to bottom, black, #333333);");
                }
            }
        });
    }


    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        songsIV.setImage(new Image("MusicAntzIpodWithBackground.png"));


        songsIV.setPreserveRatio(true);
        songsList = controller.getAllSongs();
        songsLV.setItems(songsList);
        songsLV.setPrefWidth(WIDTH);

        songsLV.setStyle("-fx-control-inner-background: #1a1a1a;");

        songsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedSong(newVal));

        genreTextField = new TextField();
        artistTextField = new TextField();

        // Set its mouse dragged event to call filter()
        yearSlider.setOnMouseDragged(e -> filter());

        // Set Button actions / highlight Edit and Remove visibility until you click Song
        addButton.setOnAction(e -> addSong());
        randomSongButton.setOnAction(e -> chooseRandomSong());

        removeButton.setDisable(true);
        removeButton.setOnAction(e -> removeSong());

        editButton.setDisable(true);
        editButton.setOnAction(e -> editSong());

        // Give Buttons Linear Gradient
        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        addButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        yearToggle.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        editButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        randomSongButton.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");


        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333);");
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.add(songsIV, 0, 0, 2, 1);

        Label genreLabel = new Label("Genre:");
        genreLabel.setStyle("-fx-text-fill: #90EE90;");
        pane.add(genreLabel, 0, 1);

        Label artistLabel = new Label("Artist:");
        artistLabel.setStyle("-fx-text-fill: #90EE90;");
        pane.add(artistLabel, 0, 2);

        pane.add(genreTextField, 1, 1);
        pane.add(artistTextField, 1, 2);

        genreTextField.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");
        artistTextField.setStyle("-fx-background-color: linear-gradient(to bottom, black, #333333); -fx-text-fill: #90EE90;");

        genreTextField.textProperty().addListener((obs, oldVal, newVal) -> filter());
        artistTextField.textProperty().addListener((obs, oldVal, newVal) -> filter());


        // Year Pane
        Label yearLabel = new Label("Year:");
        yearLabel.setStyle("-fx-text-fill: #90EE90;");
        pane.add(yearLabel, 0, 3);
        pane.add(yearSlider, 1, 3);
        pane.add(yearToggle, 2, 3);

        // Year slider
        yearSlider.setShowTickMarks(true);
        yearSlider.setShowTickLabels(true);
        yearSlider.setSnapToTicks(true);
        yearSlider.setMajorTickUnit(5.0f);
        yearSlider.setMinorTickCount(4);

        // Toggle for Year Slider
        yearToggle.setSelected(false);
        toggleYearSlider();
        yearToggle.setOnAction(e -> toggleYearSlider());

        // Button / List placement
        pane.add(addButton, 0, 5, 2, 1);
        pane.add(songsLV, 0, 6, 2, 1);
        pane.add(removeButton, 0, 7, 2, 1);
        pane.add(editButton, 0, 8, 1, 1);
        pane.add(randomSongButton, 0, 9, 1, 1);


        this.setRoot(pane);
    }

    private void updateSelectedSong(Song newVal) {
        selectedSong = newVal;
        removeButton.setDisable(selectedSong == null);
        editButton.setDisable(selectedSong == null);

    }

    /**
     * Allows the user to remove an existing song.
     * However, if the selected song is null, just return (do nothing)
     * Also updates song list after removing.
     *
     */
    private void removeSong() {
        if (selectedSong == null)
            return;

        controller.removeSong(selectedSong);
        songsList = controller.getAllSongs();
        songsLV.setItems(songsList);
        songsLV.getSelectionModel().select(-1);
    }

    private void editSong() {
        Song selectedSong = songsLV.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            ViewNavigator.loadScene("Edit Song", new com.musicantz.musicantz.view.EditScene(selectedSong));
        }
    }

    /**
     * Allows the user to add a new Song by navigating to the AddScene.
     *
     */
    private void addSong() {
        // Use the ViewNavigator to load the AddScene
        ViewNavigator.loadScene("Add Scene", new com.musicantz.musicantz.view.AddScene(this));

        songsList = controller.getAllSongs();
        songsLV.setItems(songsList);
        songsLV.getSelectionModel().select(-1);
    }

    /**
     * Toggles the state of the year slider based on the selected state of the year toggle button.
     * If the year slider is disabled, it resets the selected year to the minimum value.
     * Finally, it triggers the filter method to update the song list based on the new state of the year slider.
     *
     */
    private void toggleYearSlider() {
        boolean isYearSliderEnabled = yearToggle.isSelected();
        yearSlider.setDisable(!isYearSliderEnabled);

        // If the year slider is disabled, reset the selected year to the minimum value
        if (!isYearSliderEnabled) {
            yearSlider.setValue(yearSlider.getMin());
        }

        // Trigger the filter method to update the song list based on the new state of the year slider
        filter();
    }

    /**
     * Reads the necessary information (genre / year)
     * in order to send the criteria to the Controller for filtering.
     *
     */
    private void filter() {

        int year = (int) yearSlider.getValue();
        String artist = artistTextField.getText();
        String genre = genreTextField.getText();

        boolean isYearFilterEnabled = yearToggle.isSelected(); // Check the state of the year toggle

        songsList = controller.filter(genre, year, artist, isYearFilterEnabled);
        songsLV.setItems(songsList);
    }

    private void chooseRandomSong() {
        int numberOfSongs = songsList.size();

        // If there are no Songs do nothing
        if (numberOfSongs == 0) {
            return;
        }

        // Generate a random index to select a random song
        int randomIndex = (int) (Math.random() * numberOfSongs);

        // Get the random song
        Song randomSong = songsList.get(randomIndex);

        ViewNavigator.loadScene("Random Song Details", new com.musicantz.musicantz.view.RandomSongDetailsScene(randomSong));


    }

}
