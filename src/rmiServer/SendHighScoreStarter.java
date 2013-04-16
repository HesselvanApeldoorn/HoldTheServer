package rmiServer;


import java.net.URI;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
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

import rmiBase.HighScoreHost;
import rmiBase.RmiStarter;


public class SendHighScoreStarter extends RmiStarter {

	public final static String ipAddress = "145.97.185.24";
	public final static int port = 2626;
	
	public SendHighScoreStarter() {
		super(HighScoreHost.class);
		new HighScoreDao();
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    // Fluent interfaces
	    System.out.println(service.path("rest").path("HighScores").accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString());
	    System.out.println(service.path("rest").path("HighScores").path("new").accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString());
	    System.out.println("a");
	    System.out.println(HighScoreDao.contentProvider.values());
		ArrayList<String> names = new ArrayList<String>();
		names.add("Wico");
		names.add("Toto");
		names.add("Africa");
		System.out.println("Total number of polls:" + service.path("rest").path("HighScores").path("count").accept(MediaType.TEXT_HTML).get(String.class));
	    System.out.println(service.path("rest").path("HighScores").accept(MediaType.TEXT_PLAIN).put(
	    		ClientResponse.class, new HighScore("3", 30, new int[]{40,30, 24},names)).toString());
		System.out.println("Total number of polls:" + service.path("rest").path("HighScores").path("count").accept(MediaType.TEXT_HTML).get(String.class));

	    System.out.println(HighScoreDao.contentProvider.values());

	    System.out.println("b");
	}

	private static URI getBaseURI() {
		 return UriBuilder.fromUri("http://129.125.74.37:8080/HoldTheServer").build();
	}

	@Override
	public void start() {
		try {
			HighScoreHost engine = new SendHighScore();
			HighScoreHost engineStub = (HighScoreHost) UnicastRemoteObject.exportObject(engine, 0);
			LocateRegistry.createRegistry(SendHighScoreStarter.port);  
			Naming.rebind("rmi://"+SendHighScoreStarter.ipAddress +":"+SendHighScoreStarter.port+"/"+HighScoreHost.SERVICE_NAME, engineStub); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SendHighScoreStarter();
	}
}