package rmiServer;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import model.HighScoreDao;

import rmiBase.HighScoreHost;
import rmiBase.RmiStarter;


public class SendHighScoreStarter extends RmiStarter {

	public final static String ipAddress = "129.125.157.86";
	public final static int port = 2626;
	
	public SendHighScoreStarter() {
		super(HighScoreHost.class);
		new HighScoreDao();
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