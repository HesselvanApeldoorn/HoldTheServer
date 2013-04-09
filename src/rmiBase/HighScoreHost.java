package rmiBase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.HighScore;



public interface HighScoreHost extends Remote {
    
    public static final String SERVICE_NAME = "SendHost";
    
    //<T> void executeTask(Task<T> t) throws RemoteException;

	void executeTask(Task<HighScore> t, HighScore h) throws RemoteException;
}