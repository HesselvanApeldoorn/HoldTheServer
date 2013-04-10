package rmiClient;

import java.io.Serializable;
import java.util.ArrayList;

import model.HighScore;
import model.HighScoreDao;

import rmiBase.HighScoreTask;
import server.HighScoresResource;

public class HighScoreList implements HighScoreTask<HighScore>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8379700026436615556L;
	/**
	 * 
	 */
	
	public HighScoreList() {System.out.println("hostlist constructor");}
	
	public void execute(HighScore h) {
		HighScoreDao.contentProvider.put(h.getId(), h);
		System.out.println(h.getId());
		System.out.println(HighScoreDao.contentProvider.values());
		HighScoresResource.add(HighScoreDao.contentProvider);
	}
	public void getHost() {
		
	}

}
