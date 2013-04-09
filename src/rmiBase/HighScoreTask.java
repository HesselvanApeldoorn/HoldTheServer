package rmiBase;

import model.HighScore;

public interface HighScoreTask<T> {
    void execute(HighScore h);
}