package networking.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import networking.rmi.common.*;

public class CallbackImpl
  extends UnicastRemoteObject 
  implements ICallback {
	public CallbackImpl() throws RemoteException {
		super();
	}

	public void komunikuj(String nick, String text) throws RemoteException {
		System.out.println("odebrano komunikat: " + text);
	}
}