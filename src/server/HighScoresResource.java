package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.Endpoint;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.http.HTTPBinding;

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
	
	public HighScoresResource() {
		System.out.println("new highscoredao");
//		new HighScoreDao();
	}
	
	
	// Return the list of HighScores to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public static List<HighScore> getHighScoresBrowser() {
		List<HighScore> HighScores = new ArrayList<HighScore>();
		if(HighScoreDao.contentProvider != null) {
			HighScores.addAll(HighScoreDao.contentProvider.values());
		}
		return HighScores; 
	}
	
	// Return the list of HighScores for applications
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public static List<HighScore> getHighScores() {
	 List<HighScore> highScores = new ArrayList<HighScore>();
	 highScores.addAll(HighScoreDao.contentProvider.values());
	 return highScores; 
	}
	
	@GET
	@Path("new")
	@Produces(MediaType.TEXT_HTML)
	public static String getNew() {
		new HighScoreDao();
		return "made new HighScoreDao";
	}
	
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_HTML)
	public static String getCount() {
		int count = 0;
		if(HighScoreDao.contentProvider!=null) {
			count = HighScoreDao.contentProvider.size();
		}
		return String.valueOf(count);
	}
	
	private boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public static String sayHtmlHello() {
//		new HighScoreDao();
		String output = "";
		output += "<html> " + "<head>" + "<title>" + "HighScore system" + "</title>" +
				"</head>" + "<body>";
	    output += "<h2>Highscores:</h2>";
	    output += HighScoreDao.contentProvider;
	    for(int i=0; i<getHighScoresBrowser().size();i++) {
	        output += "<br>Maximum points: <a href='/RESTHighScore/rest/HighScores/" + getHighScoresBrowser().get(i).getId() + "'> " + getHighScoresBrowser().get(i).getMaxPoints() + "</a>";
	        HighScore highScore = getHighScoresBrowser().get(i);
	        output += "<table border='1'> <tr><th>Name</th><th>Score</th></tr>";
	        for(int j=0; j<highScore.getScores().size(); j++) {
	        	output += "<tr><td>" + highScore.getNames().get(j) + "</td><td>" + highScore.getScores().get(j) + "</td></tr>";
	        }
	        output += "</table><br><br>";
	    }
	    output += "<br>";
	    output += "</body>" + "</html> ";
		return output;
	}
	
	 @PUT
	 @Consumes(MediaType.APPLICATION_XML)
    public void update(JAXBElement<HighScore> highScore) {
			System.out.println("in update");
	        HighScoreDao.contentProvider.put(highScore.getValue().getId(), highScore.getValue());
	        System.out.println(HighScoreDao.contentProvider.values());
    }
}