package networking.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author PLJABEN
 *
 */
public class RMIServer {
	Registry reg; // rejestr nazw obiektow
	RMIServant servant; // klasa uslugowa

	public static void main(String[] args) {
		try {
			new RMIServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	protected RMIServer() throws RemoteException {
		try {
			reg = LocateRegistry.createRegistry(1099); // Utworzenie rejestru nazw
			servant = new RMIServant(); // utworzenie zdalnego obiektu
			reg.rebind("RMIServer", servant); // zwiazanie nazwy z obiektem
			System.out.println("RMIServer READY");
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}
}