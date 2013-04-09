package rmiBase;

import java.util.ArrayList;

import model.HighScore;

public interface Task<T> {
    void execute(HighScore knownHosts);
}