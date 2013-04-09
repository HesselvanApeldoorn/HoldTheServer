package rmiServer;


import java.rmi.RemoteException;
import java.util.ArrayList;

import model.HighScore;
import model.HighScoreDao;

import rmiBase.HighScoreHost;
import rmiBase.Task;


public class SendHost implements HighScoreHost {

	@Override
	public void executeTask(Task<HighScore> t, HighScore h) throws RemoteException {
		t.execute(h);
	}


}