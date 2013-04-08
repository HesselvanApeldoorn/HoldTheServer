package server;

public class HighScoreEngineStarter extends RmiStarter {

	public HighScoreEngineStarter() {
		super(Compute.class);
	}

	@Override
	public void start() {
		try {
			Compute engine = new ComputeEngine();
			Compute engineStub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(Compute.SERVICE_NAME, engineStub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new HighScoreEngineStarter();
	}
}
