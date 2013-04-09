package rmiClient;

import java.io.Serializable;
import java.util.ArrayList;

import model.HighScore;
import model.HighScoreDao;

import rmiBase.Task;

public class HighScoreList implements Task<HighScore>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8379700026436615556L;
	/**
	 * 
	 */
	
	public HighScoreList() {System.out.println("hostlist constructor");}
	
	public void execute(HighScore h) {
		HighScoreDao.instance.getModel().put(h.getId(), h);
		//		ArrayList<String> list = new ArrayList<String>();
//		list.add("naam");
//		HighScore room = new HighScore("Gasdrsdfgap", 5 , list); 
//		knownHosts.add(room);
//		return knownHosts;
	}
	public void getHost() {
		
	}

}
