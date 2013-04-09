package rmiServer;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import rmiBase.HighScoreHost;
import rmiBase.RmiStarter;


/**
 * start the server component. this exposes the an implementation of the Compute
 * interface as a service over RMI An RMI server program needs to create the
 * initial remote objects and export them to the RMI runtime, which makes them
 * available to receive incoming remote invocations.
 * 
 * @author srasul
 * 
 */
public class SendHighScoreStarter extends RmiStarter {

	public SendHighScoreStarter() {
		super(HighScoreHost.class);
	}

	@Override
	public void start() {
		try {
			HighScoreHost engine = new SendHighScore();
			
			HighScoreHost engineStub = (HighScoreHost) UnicastRemoteObject.exportObject(engine, 0);
			LocateRegistry.createRegistry(2525);  
			Naming.rebind("rmi://129.125.41.177:2626/"+HighScoreHost.SERVICE_NAME, engineStub); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SendHighScoreStarter();
	}
}