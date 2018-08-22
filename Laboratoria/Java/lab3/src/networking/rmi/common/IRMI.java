package networking.rmi.common;

import java.rmi.*;
import java.util.List;
import projekt.*;

public interface IRMI<T> extends Remote {
	List zapiszDoListy(List list, String type, TimeHistory TH, Spectrum S) throws RemoteException;
	String zobaczListe(List list, String reqDevName, String reqChNr, String reqType) throws RemoteException;
	boolean zapiszDoKatalogu(List list, String type, int id) throws RemoteException;
	byte[] wyswietlZListy(List list, String type, int id) throws RemoteException;
	boolean zarejestruj(String nick, ICallback n) throws RemoteException;
	boolean wyrejestruj(String nick) throws RemoteException;
}
