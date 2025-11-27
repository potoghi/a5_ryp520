package application.model;

import application.view.MainView;
import javafx.scene.image.Image;
import java.util.Random;

public class Mole implements Runnable {

    public WhackAMole game;
    public MainView mainView;
    public Image moleImage; // Image for "Mole Up"
    public Image emptyImage; // Image for "Hole empty"
    public int index;
    public Random rand;

    public Mole(WhackAMole game, MainView mainView, int index) {
        this.game = game;
        this.mainView = mainView;
        this.index = index;
        this.rand = new Random();

        // Load images (Ensure these files exist in your resources)
        // Using placeholders for this code snippet
        try {
            this.moleImage = new Image(getClass().getResourceAsStream("/mole.png"));
            this.emptyImage = new Image(getClass().getResourceAsStream("/hole.png"));
        } catch (Exception e) {
            System.out.println("Images not found, ensure mole.png and hole.png are in resources.");
        }
    }

    @Override
    public synchronized void run() {
        while (!game.gameOver()) {
            try {
                // 1. Stay hidden for a random time
                mainView.displayImage(index, emptyImage);
                game.setExposed(index, false);
                Thread.sleep(500 + rand.nextInt(2000));

                if (game.gameOver()) break;

                // 2. Pop up!
                mainView.displayImage(index, moleImage);
                game.setExposed(index, true);

                // 3. Stay up for a random time (unless whacked)
                Thread.sleep(500 + rand.nextInt(1000));

                // 4. Go back down
                game.setExposed(index, false);
                mainView.displayImage(index, emptyImage);

            } catch (InterruptedException e) {
                // Thread interrupted
            }
        }
        // Ensure image is cleared when game ends
        mainView.displayImage(index, emptyImage);
    }
}