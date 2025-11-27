package application.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainView {

    public Label timeRemainingLabel;
    public Label scoreLabel;
    public ImageView[] imageViews;

    public MainView(Label timeLabel, Label scoreLabel, ImageView[] imageViews) {
        this.timeRemainingLabel = timeLabel;
        this.scoreLabel = scoreLabel;
        this.imageViews = imageViews;
    }

    /**
     * Updates the time label. Uses Platform.runLater to ensure thread safety.
     */
    public void displayTimeRemaining(String time) {
        Platform.runLater(() -> {
            timeRemainingLabel.setText(time);
        });
    }

    /**
     * Updates the score label.
     */
    public void displayLabel(String score) {
        Platform.runLater(() -> {
            scoreLabel.setText(score);
        });
    }

    /**
     * Updates the image at a specific index (Mole up or down).
     */
    public void displayImage(int index, Image image) {
        Platform.runLater(() -> {
            if (index >= 0 && index < imageViews.length) {
                imageViews[index].setImage(image);
            }
        });
    }
}