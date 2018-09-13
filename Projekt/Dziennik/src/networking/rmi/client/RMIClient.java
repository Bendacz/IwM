package networking.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.rmi.api.Service;

public class RMIClient extends Application {

	private Service personService;
	@Override
	public void start(Stage stage) throws Exception {
		Registry registry = LocateRegistry.getRegistry("localhost", 6789);
		personService = (Service) registry.lookup("service");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
		Parent root = loader.load();
		LoginFormController controller = loader.getController();
		controller.setMain(this);
		stage.setScene(new Scene(root));
		stage.setTitle("Dziennik Elektroniczny");
		stage.show();
	}

	public Service getPersonService() {
		return personService;
	}

	public RMIClient getClient() {
		return this;
	}

	public static void main(String[] args) {
		launch(args);
	}

}