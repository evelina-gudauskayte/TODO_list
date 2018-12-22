package BL.Listeners;

import javafx.scene.control.ProgressBar;

public class ProgressBarListener implements ProgressListener {
    private ProgressBar progressBar;

    public ProgressBarListener(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void inProgressChange(int progress, int total) {
        progressBar.setProgress((double) progress / total);
    }
}
