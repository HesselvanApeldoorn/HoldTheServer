package rmiBase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.HighScore;



public interface HighScoreHost extends Remote {
    
    public static final String SERVICE_NAME = "SendHighScore";
    
    //<T> void executeTask(Task<T> t) throws RemoteException;

	void executeTask(HighScoreTask<HighScore> t, HighScore h) throws RemoteException;
}