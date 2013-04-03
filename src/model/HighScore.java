package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HighScore implements Serializable {
	
	private static final long serialVersionUID = 1556065201700688552L;
	private String id;
	private int maxPoints;
	private int[] scores;
	private ArrayList<String> names; 
	
	public HighScore(String id, int maxPoints, int[] scores, ArrayList<String> names) {
		this.setId(id);
		this.setMaxPoints(maxPoints);
		this.setScores(scores);
		this.setNames(names);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
