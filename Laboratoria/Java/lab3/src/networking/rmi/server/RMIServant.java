package networking.rmi.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import projekt.*;
import networking.rmi.common.*;
import networking.rmi.common.Tools;

public class RMIServant<T>
  extends UnicastRemoteObject 
  implements IRMI<T> {
	private Map<String, ICallback> obecni = new HashMap<String, ICallback>();

	public RMIServant() throws RemoteException {
	}
	
	public synchronized List zapiszDoListy(List list, String type, TimeHistory TH, Spectrum S) {	
		switch(type) {
		case "t":
			list.add(TH);
			break;
		case "s":
			list.add(S);
			break;
		default:
			System.out.println("Zła komenda");
		}
		return list;
	}
	
	public synchronized String zobaczListe(List list, String reqDevName, String reqChNr, String reqType) {
		String listString;
		if (list.isEmpty()) {
			listString = "Lista jest pusta";
		}
		else {
			listString = "Lista plików spełniających kryterium wyszukiwania: ";
        	Integer count = 0;
	        for(int i = 0; i < list.size(); i++) {
				switch(reqType) {
				case "t":
		        	TimeHistory TH = (TimeHistory)list.get(i);   	
		        	if ((reqDevName.equals(TH.getDevName()) || reqDevName.equals("-"))  && (reqChNr.equals(TH.getChNr().toString()) || reqChNr.equals("-"))) {
		        		count = count + 1;
		        		listString = listString.concat("\n" + count.toString() + ". " + TH.getFileName() + ".thi");
					}
					break;
				case "s":
		        	Spectrum S = (Spectrum)list.get(i);   	
		        	if ((reqDevName.equals(S.getDevName()) || reqDevName.equals("-"))  && (reqChNr.equals(S.getChNr().toString()) || reqChNr.equals("-"))) {
		        		count = count + 1;
		        		listString = listString.concat("\n" + count.toString() + ". " + S.getFileName() + ".spc");
					}
					break;
				default:
					System.out.println("Zła komenda");
				}
	        }
		}
        return listString;
	}

	public synchronized boolean zapiszDoKatalogu(List list, String type, int id){
		try {
			String fileName;
			switch(type) {
			case "t":
				TimeHistory TH = (TimeHistory)list.get(id-1);
				fileName = TH.getFileName();
				System.out.println("Zapisanie pliku " + fileName + ".thi");
				Files.write(new File(fileName + ".thi").toPath(), Tools.serialize(TH));
				break;
			case "s":
				Spectrum S = (Spectrum)list.get(id-1);
				fileName = S.getFileName();
				System.out.println("Zapisanie pliku " + fileName + ".spc");
				Files.write(new File(fileName + ".spc").toPath(), Tools.serialize(S));
				break;
			default:
				System.out.println("Zła komenda");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public synchronized byte[] wyswietlZListy(List list, String type, int id){
		byte[] buffer = new byte[1024];
		switch(type) {
		case "t":
			TimeHistory TH = (TimeHistory)list.get(id-1);
			buffer = Tools.serialize(TH);
			break;
		case "s":
			Spectrum S = (Spectrum)list.get(id-1);
			buffer = Tools.serialize(S);
			break;
		default:
			System.out.println("Zła komenda");
		}
	return buffer;
	}

	public boolean zarejestruj(String nick, ICallback n) throws RemoteException {
		System.out.println("Server.zarejestruj(): " + nick);
		if (!obecni.containsKey(nick)) {
			obecni.put(nick, n);
			return true;
		}
		return false;
	}

	public boolean wyrejestruj(String nick) throws RemoteException {
		if (obecni.remove(nick) != null) {
			System.out.println("Server.wyrejestruj(): " + nick);
			return true;
		}
		return false;
	}
}