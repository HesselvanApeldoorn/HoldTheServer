package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HighScoreDao {

  
  public static Map<String, HighScore> contentProvider;
  
  public HighScoreDao() {
	  	contentProvider = new HashMap<String, HighScore>();
		ArrayList<String> names = new ArrayList<String>();
		names.add("Wico");
		names.add("Toto");
		names.add("Africa");
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(40);
		scores.add(30);
		scores.add(24);
	    HighScore highScore = new HighScore("1", 30, scores, names);
	    contentProvider.put("1", highScore);
		ArrayList<String> names2 = new ArrayList<String>();
		names2.add("Bert");
		names2.add("Ernie");
		ArrayList<Integer> scores2 = new ArrayList<Integer>();
		scores2.add(20);
		scores2.add(12);
	    highScore = new HighScore("2", 20, scores2,names2);
	    contentProvider.put("2", highScore);
  }
  
  public static Map<String, HighScore> getModel() {
    return contentProvider;
  }
}