package rmiServer;


import java.rmi.RemoteException;
import java.util.ArrayList;

import model.HighScore;
import model.HighScoreDao;

import rmiBase.HighScoreHost;
import rmiBase.HighScoreTask;


public class SendHighScore implements HighScoreHost {

	@Override
	public void executeTask(HighScoreTask<HighScore> t, HighScore h) throws RemoteException {
		t.execute(h);
	}


}