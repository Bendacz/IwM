package networking.rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.stage.Stage;

public class RMIServer extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		DatabaseConnection.getConnection();

		Registry registry = LocateRegistry.createRegistry(6789);

		ServiceImpl serviceImpl = new ServiceImpl();

		//Service personService = (Service) UnicastRemoteObject.exportObject(serviceImpl, 0);

		registry.rebind("service", serviceImpl);

		System.out.println("Server is running");
	}

	public static void main(String[] args) {
		launch(args);
	}

}