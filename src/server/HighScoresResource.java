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
	
	
	// Return the list of HighScores to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public static List<HighScore> getHighScoresBrowser() {
		List<HighScore> HighScores = new ArrayList<HighScore>();
		HighScores.addAll(HighScoreDao.contentProvider.values());
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
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public static String getCount() {
		//int count = HighScoreDao.contentProvider.size();
		Hello hello = new Hello("foi");
		return hello.sayHello();
//		return String.valueOf(count);
	}
	
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public static String sayHtmlHello() {
		new HighScoreDao();
		String output = "";
		output += "<html> " + "<head>" + "<title>" + "HighScore system" + "</title>" +
				"</head>" + "<body>";
	    output += "<h2>Highscores:</h2>";
	    output += HighScoreDao.contentProvider;
	    for(int i=0; i<getHighScoresBrowser().size();i++) {
	        output += "<br>Maximum points: <a href='/RESTHighScore/rest/HighScores/" + getHighScoresBrowser().get(i).getId() + "'> " + getHighScoresBrowser().get(i).getMaxPoints() + "</a>";
	        HighScore highScore = getHighScores().get(i);
	        output += "<table border='1'> <tr><th>Name</th><th>Score</th></tr>";
	        for(int j=0; j<highScore.getScores().length; j++) {
	        	output += "hoi";//"<tr><td>" + highScore.getNames().get(j) + "</td><td>" + highScore.getScores()[j] + "</td></tr>";
	        }
	        output += "</table><br><br>";
	    }
	    output += "<br>";
	    output += "</body>" + "</html> ";
		return output;
	}
	
	 @PUT
	 @Consumes(MediaType.APPLICATION_XML)
    public void update(JAXBElement<Map<String, HighScore>> cp) {
			System.out.println("in update");
	        HighScoreDao.contentProvider=cp.getValue();
    }
	
//	@PUT
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.APPLICATION_XML)
//    public static void update(Map<String, HighScore> cp) {
//		System.out.println("in update");
//        HighScoreDao.contentProvider=cp;
//    }
	
	public static void add(Map<String, HighScore> contentProvider2) {
		HighScoreDao.contentProvider = contentProvider2;
		System.out.println(HighScoreDao.contentProvider.values());
//		update((JAXBElement<Map<String, HighScore>>) contentProvider2);
		System.out.println(getCount());
		System.out.println(sayHtmlHello());
	      Endpoint e = Endpoint.create(
                  new Hello("hoiii"));
	      System.out.println("waar foute?");
//	      e.publish("http://192.168.178.11:8080/HoldTheServer/rest/HighScores");
//	      e.publish("http://192.168.178.11:8081/HoldTheServer/plek");
	      
	}
}