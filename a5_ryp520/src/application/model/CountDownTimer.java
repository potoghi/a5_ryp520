package application.model;

import application.view.MainView;

public class CountDownTimer implements Runnable {

    public WhackAMole game;
    public MainView mainView;
    public int durationSeconds;

    public CountDownTimer(WhackAMole game, MainView mainView, int durationSeconds) {
        this.game = game;
        this.mainView = mainView;
        this.durationSeconds = durationSeconds;
    }

    @Override
    public synchronized void run() {
        while (durationSeconds > 0) {
            if (game.gameOver()) break;

            try {
                mainView.displayTimeRemaining("Time: " + durationSeconds);
                Thread.sleep(1000); // Wait 1 second
                durationSeconds--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mainView.displayTimeRemaining("Time: 0");
        game.endGame();
    }
}