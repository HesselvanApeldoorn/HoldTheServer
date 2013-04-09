package server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.HighScore;
import model.HighScoreDao;

@Path("/HighScores")
public class HighScoresResource {

	// Allows to insert contextual objects into the class, 
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	// Return the list of HighScores to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<HighScore> getHighScoresBrowser() {
		List<HighScore> HighScores = new ArrayList<HighScore>();
		HighScores.addAll(HighScoreDao.getModel().values());
		return HighScores; 
	}
	
	// Return the list of HighScores for applications
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<HighScore> getHighScores() {
	 List<HighScore> highScores = new ArrayList<HighScore>();
	 highScores.addAll(HighScoreDao.getModel().values());
	 return highScores; 
	}
	
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = HighScoreDao.getModel().size();
		return String.valueOf(count);
	}
	
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		String output = "";
		output += "<html> " + "<head>" + "<title>" + "HighScore system" + "</title>" +
				"</head>" + "<body>";
	    output += "<h2>Highscores:</h2>";
	    for(int i=0; i<this.getHighScoresBrowser().size();i++) {
	        output += "<br>Maximum points: <a href='/RESTHighScore/rest/HighScores/" + this.getHighScoresBrowser().get(i).getId() + "'> " + this.getHighScoresBrowser().get(i).getMaxPoints() + "</a>";
	        HighScore highScore = this.getHighScores().get(i);
	        output += "<table border='1'> <tr><th>Name</th><th>Score</th></tr>";
	        for(int j=0; j<highScore.getScores().length; j++) {
	        	output += "<tr><td>" + highScore.getNames().get(j) + "</td><td>" + highScore.getScores()[j] + "</td></tr>";
	        }
	        output += "</table><br><br>";
	    }
	    output += "<br>";
	    output += "</body>" + "</html> ";
		return output;
	}
}