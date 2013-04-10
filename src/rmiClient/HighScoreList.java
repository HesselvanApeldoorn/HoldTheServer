package rmiClient;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

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
		System.out.println(h.getId());
		System.out.println(HighScoreDao.contentProvider.values());
		HighScoresResource.add(HighScoreDao.contentProvider);
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    // Fluent interfaces
//	    System.out.println(service.path("rest").path("HighScores").accept(MediaType.APPLICATION_XML).get(ClientResponse.class).toString());
//	    System.out.println("a");
//	    System.out.println(service.path("rest").path("HighScores").accept(MediaType.APPLICATION_XML).put(ClientResponse.class, HighScoreDao.contentProvider));
//	    System.out.println("b");
	}
	 private static URI getBaseURI() {
		 return UriBuilder.fromUri("http://192.168.178.11:8080/HoldTheServer").build();
	 }

}
