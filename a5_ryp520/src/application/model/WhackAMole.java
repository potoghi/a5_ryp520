package application.model;

import application.view.MainView;
import java.util.Random;

public class WhackAMole {

    public MainView mainView;
    public CountDownTimer timer;
    public Mole[] moles;
    public Thread[] moleThreads;
    public boolean[] exposed;
    public Random rand;
    public int totalScore;
    public boolean gameOver;

    public WhackAMole(MainView mainView) {
        this.mainView = mainView;
        this.rand = new Random();
        this.totalScore = 0;
        this.gameOver = true; // Game starts in stopped state

        // Initialize arrays based on the 5 ImageViews seen in Controller
        int numMoles = 5;
        this.moles = new Mole[numMoles];
        this.moleThreads = new Thread[numMoles];
        this.exposed = new boolean[numMoles];
    }

    public void startGame() {
        if (!gameOver) return; // Prevent double start

        this.gameOver = false;
        this.totalScore = 0;
        this.mainView.displayLabel("Score: 0");

        // Reset exposed status
        for(int i=0; i<exposed.length; i++) exposed[i] = false;

        // Initialize and start Timer
        this.timer = new CountDownTimer(this, mainView, 30); // 30 second game
        new Thread(timer).start();

        // Initialize and start Moles
        for (int i = 0; i < moles.length; i++) {
            moles[i] = new Mole(this, mainView, i);
            moleThreads[i] = new Thread(moles[i]);
            moleThreads[i].start();
        }
    }

    public void endGame() {
        this.gameOver = true;
        this.mainView.displayLabel("Final Score: " + totalScore);
    }

    public boolean gameOver() {
        return this.gameOver;
    }

    // Called by Mole threads
    public void setExposed(int index, boolean isExposed) {
        this.exposed[index] = isExposed;
    }

    // Called when user clicks a mole
    public synchronized void updateScore(int responseTimeMillis) {
        // Logic: if response time is fast, get more points (example logic)
        // For basic whack-a-mole, we usually just increment
        this.totalScore += 10;
        this.mainView.displayLabel("Score: " + totalScore);
    }

    // Helper method for the Controller to call when a button is clicked
    public void handleWhack(int index) {
        if (!gameOver && exposed[index]) {
            updateScore(0);
            exposed[index] = false; // Hide immediately on hit
            // Ideally, you would also notify the specific Mole thread here to reset
        }
    }
}