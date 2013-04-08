package server;
import java.rmi.RemoteException;

public class HighScoreEngine implements Compute {

	@Override
	public <T> T executeTask(Task<T> t) throws RemoteException {
		System.out.println("got compute task: " + t);
		return t.execute();
	}
}