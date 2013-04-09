package server;

import model.HighScore;

public interface Task<T> {
    T execute(HighScore h);
}
