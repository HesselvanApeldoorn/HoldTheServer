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

	    String amountScores = service.path("rest").path("HighScores").path("count").accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString();
	    int amountHighScore = 0;
	    if(isInteger(amountScores)) {
	    	amountHighScore = Integer.parseInt(amountScores);
	    } else {
	    	amountHighScore = 0;
	    }
	    if(amountHighScore <= 0) {
		    service.path("rest").path("HighScores").path("new").accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString();
	    }
	}

	private boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}

	private static URI getBaseURI() {
		 return UriBuilder.fromUri("http://"+ipAddress+":8080/HoldTheServer").build();
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