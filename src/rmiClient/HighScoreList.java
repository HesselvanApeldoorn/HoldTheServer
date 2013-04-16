package rmiClient;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import model.HighScore;
import model.HighScoreDao;

import rmiBase.HighScoreTask;
import rmiServer.SendHighScoreStarter;
import server.HighScoresResource;

public class HighScoreList implements HighScoreTask<HighScore>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8379700026436615556L;
	/**
	 * 
	 */
		
	public void execute(HighScore h) {
		HighScoreDao.contentProvider.put(h.getId(), h);
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    // Fluent interfaces
//	    System.out.println(service.path("rest").path("HighScores").accept(MediaType.APPLICATION_XML).get(ClientResponse.class).toString());
//	    System.out.println("a");
	    System.out.println(service.path("rest").path("HighScores").accept(MediaType.TEXT_PLAIN).put(ClientResponse.class, h).toString());
	    Random random = new Random();
	    System.out.println(random.nextInt(9000));
	    //		System.out.println("Total number of polls:" + service.path("rest").path("HighScores").path("count").accept(MediaType.TEXT_HTML).get(String.class));

	    //	    System.out.println("b");
	}
	 private static URI getBaseURI() {
		 return UriBuilder.fromUri("http://"+SendHighScoreStarter.ipAddress+":8080/HoldTheServer").build();
	 }

}
