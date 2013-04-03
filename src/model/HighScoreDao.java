package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public enum HighScoreDao {
  instance;
  
  private Map<String, HighScore> contentProvider = new HashMap<String, HighScore>();
  
  private HighScoreDao() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Wico");
		names.add("Toto");
		names.add("Africa");
	    HighScore highScore = new HighScore("1", 30, new int[]{40,30, 24},names);
	    contentProvider.put("1", highScore);
		ArrayList<String> names2 = new ArrayList<String>();
		names2.add("Bert");
		names2.add("Ernie");
	    highScore = new HighScore("1", 20, new int[]{20,15},names2);
	    contentProvider.put("2", highScore);
  }
  
  public Map<String, HighScore> getModel() {
    return contentProvider;
  }
}