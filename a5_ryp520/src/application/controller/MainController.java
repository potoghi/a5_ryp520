package application.controller;

import application.model.WhackAMole;
import application.view.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController {

    public WhackAMole game;

    @FXML public Label timeRemainingLabel;
    @FXML public Label scoreLabel;

    // ImageViews as per UML
    @FXML public ImageView imageView0;
    @FXML public ImageView imageView1;
    @FXML public ImageView imageView2;
    @FXML public ImageView imageView3;
    @FXML public ImageView imageView4;

    // Panes as per UML (Containers for the images)
    @FXML public Pane pane0;
    @FXML public Pane pane1;
    @FXML public Pane pane2;
    @FXML public Pane pane3;
    @FXML public Pane pane4;

    @FXML
    public void initialize() {
        // Collect ImageViews into an array for the View class
        ImageView[] imageViews = {imageView0, imageView1, imageView2, imageView3, imageView4};

        // Initialize the View
        MainView view = new MainView(timeRemainingLabel, scoreLabel, imageViews);

        // Initialize the Game Model with the View
        this.game = new WhackAMole(view);

        // Set up click listeners for the images (Mole Whacking)
        setupClickHandlers();
    }

    private void setupClickHandlers() {
        imageView0.setOnMouseClicked(e -> imageViewAction(0));
        imageView1.setOnMouseClicked(e -> imageViewAction(1));
        imageView2.setOnMouseClicked(e -> imageViewAction(2));
        imageView3.setOnMouseClicked(e -> imageViewAction(3));
        imageView4.setOnMouseClicked(e -> imageViewAction(4));
    }

    // Handles the actual logic when a mole is clicked
    public void imageViewAction(int index) {
        if (game != null) {
            game.handleWhack(index);
        }
    }

    // Connect this to your "Start Game" button in SceneBuilder
    @FXML
    public void startButtonAction(ActionEvent event) {
        if (game != null) {
            game.startGame();
        }
    }
}