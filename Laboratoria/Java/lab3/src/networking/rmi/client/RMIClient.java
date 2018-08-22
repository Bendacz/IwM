package networking.rmi.client;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import networking.rmi.client.RMIClient;
import networking.rmi.common.ICallback;
import networking.rmi.common.IRMI;
import networking.rmi.common.Tools;
import projekt.*;

public class RMIClient {
	private Scanner userInput = new Scanner(System.in);
	String username;
	IRMI remoteObject; // referencja do zdalnego obiektu
	ICallback callback;
	
	public static void main(String[] args) throws IOException {
		new RMIClient("localhost");
}

	/**
	 * @param hostname
	 * @throws IOException
	 */
	/**
	 * @param hostname
	 * @throws IOException
	 */
	/**
	 * @param hostname
	 * @throws IOException
	 */
	public RMIClient(String hostname) throws IOException {
		System.out.println("Podaj nazwę klienta: ");
		if(userInput.hasNextLine())
			username = userInput.nextLine();
		Registry reg;    // rejestr nazw obiektow
		try {
			// pobranie referencji do rejestru nazw obiektow
			reg = LocateRegistry.getRegistry(hostname);
			// odszukanie zdalnego obiektu po jego nazwie
			remoteObject = (IRMI) reg.lookup("RMIServer");
			callback = new CallbackImpl();
			// wywolanie metod zdalnego obiektu
			remoteObject.zarejestruj(username, callback);
			
			List<TimeHistory> listTH = new ArrayList<>();
			List<Spectrum> listS = new ArrayList<>();
			
			listTH = wczytajStan(listTH, "t");
			listS = wczytajStan(listS, "s");
			
			String listString;
			if (listTH.isEmpty() && listS.isEmpty()) {
				listString = "Katalog serwera jest pusty";
			}
			else {
				listString = "\nLista plików typu TimeHistory znajdujących się w katalogu serwera: ";
	        	Integer count = 1;
		        for(int i = 0; i < listTH.size(); i++) {
			        TimeHistory TH = (TimeHistory)listTH.get(i);   	
			        listString = listString.concat("\n" + count.toString() + ". " + TH.getFileName() + ".thi");
			        count = count + 1;
				}
	        	
		        listString = listString.concat("\n\nLista plików typu Spectrum znajdujących się w katalogu serwera:");
		        count = 1;
		        for(int i = 0; i < listS.size(); i++) {
			        Spectrum S = (Spectrum)listS.get(i);   	
			        listString = listString.concat("\n" + count.toString() + ". " + S.getFileName() + ".spc");
			        count = count + 1;
				}
			}
			System.out.println(listString);
			loop(listTH, listS);
			remoteObject.wyrejestruj(username);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	private List zapiszDoListy(List list, String type) throws IOException {
		TimeHistory<Integer> TH = null;
		Spectrum<Integer> S = null;
		
		String dev;
		System.out.println("Podaj nazwę urządzenia: ");
		dev = userInput.next();

		String desc;
		System.out.println("Podaj opis: ");
		desc = userInput.next();		
		
		long date;
		System.out.println("Podaj czas rejestracji: ");
		date = userInput.nextLong();

		int chNr;
		System.out.println("Podaj numer kanału: ");
		chNr = userInput.nextInt();
			
		String unit;
		System.out.println("Podaj jednostkę: ");
		unit = userInput.next();

		double res;
		System.out.println("Podaj rozdzielczość: ");
		res = userInput.nextDouble();
		
		Integer[] buff = new Integer[1];
		buff[0] = 10;

		switch(type) {
		case "t":
			double sens;
			System.out.println("Podaj czułość: ");
			sens = userInput.nextDouble();
			TH = new TimeHistory<Integer>(dev,desc,date,chNr,unit,res,buff,sens);
			list = remoteObject.zapiszDoListy(list, type, TH, S);
			break;
		case "s":
			String scal;
			System.out.println("Podaj skalowanie: ");
			scal = userInput.next();
			S = new Spectrum<Integer>(dev,desc,date,chNr,unit,res,buff,scal);
			list = remoteObject.zapiszDoListy(list, type, TH, S);
			break;
		default:
			System.out.println("Zła komenda");
		}
		
		System.out.println("Czy chcesz jeszcze raz zapisać obiekt tego samego typu do listy? \n[t] tak \n[n] nie");
		String line;
		line = userInput.next();
		switch(line) {
			case "t":
				list = zapiszDoListy(list, type);
				break;
			case "n":
				break;
			default:
				System.out.println("Zła komenda");
			}
		return list;
	}
	
	private void zobaczListe(List listTH, List listS) {
		String reqDevName = null;
		String reqChNr = null;
		String reqType = null;
		String Type = null;
		List list = null;
		
		System.out.println("Podaj nazwę szukanego urządzenia ([-] wszystkie): ");
		reqDevName = userInput.next();
			
		System.out.println("Podaj numer kanału szukanego urządzenia ([-] wszystkie): ");
		reqChNr = userInput.next();
			
		System.out.println("Podaj typ: \n[t] TimeHistory \n[s] Spectrum");
		Type = userInput.next();
		switch(Type) {
			case "t":
				reqType = "thi";
				list = listTH;
				break;
			case "s":
				reqType = "spc";
				list = listS;
				break;
			default:
				System.out.println("Złą komenda");
		}
		
		String fileName = reqDevName + "_" + reqChNr + "." + reqType;
		System.out.println("Wyszukiwany plik: " + fileName);

		try {
			String listString = remoteObject.zobaczListe(list, reqDevName, reqChNr, Type);
			System.out.println(listString);
		} catch (RemoteException e) { e.printStackTrace(); }
	}
	
	private void zapiszDoKatalogu(List list, String type) {
		try {
			int flag = pokazListe(list, type);
			
			if (flag == 0){
				int id;
				System.out.println("Podaj numer obiektu z listy, który ma zostać zapisany do katalogu serwera:");
				id = userInput.nextInt();
				
				if (id > list.size() || id <= 0) {
					System.out.println("Podano złą liczbę");
				}
				else {
					remoteObject.zapiszDoKatalogu(list, type, id);
				}

				String line;
				System.out.println("Czy chcesz zapisać kolejny obiekt z tej listy do katalogu serwera? \n[t] tak \n[n] nie");
				line = userInput.next();
				switch(line) {
					case "t":
						zapiszDoKatalogu(list, type);
						break;
					case "n":
						break;
					default:
						System.out.println("Zła komenda");
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
	private void wyswietlZListy(List list, String type) {
		try {
			int flag = pokazListe(list, type);
			
			if (flag == 0) {
				byte[] buffer = new byte[1024];
				int id;
				
				System.out.println("Podaj numer obiektu z listy, kt�rego zawartość ma zostać wyświetlona:");
				id = userInput.nextInt();
				
				if (id > list.size() || id <= 0) {
					System.out.println("Podano złą liczbę");
				}
				else {
					buffer = remoteObject.wyswietlZListy(list, type, id);
					Packet temp = (Packet)Tools.deserialize(buffer);
					System.out.println("Wyświetla dane ze wskazanego pliku: ");
					System.out.println(temp.toString());
				}
				
				String line;
				System.out.println("Czy chcesz wyświetlić zawartość innego obiektu z tej listy? \n[t] tak \n[n] nie");
				line = userInput.next();
				switch(line) {
					case "t":
						wyswietlZListy(list, type);
						break;
					case "n":
						break;
					default:
						System.out.println("Zła komenda");
				}
			}
		} catch (RemoteException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private int pokazListe(List list, String reqType) throws RemoteException  {

		String reqDevName = "-";
		String reqChNr = "-";
		String listString;
		String[] temp;
		int flag = 0;
		
		if (list.isEmpty()) {
			System.out.println("Lista jest pusta");
			flag = 1;
		}
		else {
			switch(reqType) {
			case "t":
				listString = remoteObject.zobaczListe(list, reqDevName, reqChNr, reqType);
				temp = listString.split(":");
				System.out.println("Lista plików typu TimeHistory:" + temp[1]);
				break;
			case "s":
				listString = remoteObject.zobaczListe(list, reqDevName, reqChNr, reqType);
				temp = listString.split(":");
				System.out.println("Lista plików typu Spectrum:" + temp[1]);
				break;
			default:
				System.out.println("Zła komenda");
			}
		}
		return flag;
	}
	
	public List wczytajStan(List list, String type) {
		String directoryPath = new String("D:/Studia/Semestry/VIII Semestr/Informatyka w Mechatronice/Laboratoria/Java/lab3/"); //tutaj proszę umieścić swoją ścieżkę do katalogu serwera
		byte[] buffer = new byte[1024];
		String[] s;
		File dir = new File(directoryPath);
		
		if(dir.isDirectory()){
			File[] listFiles = dir.listFiles();
		    for(File file : listFiles){
		    	if(file.isFile() && !".classpath".equals(file.getName()) && !".project".equals(file.getName())) {
		    		try {
		    			switch(type) {
		    			case "t":
		    				s = file.getName().split("\\.");
		    				if (s[1].equals("thi")){
									buffer = Files.readAllBytes(new File(file.getName()).toPath());
						    		TimeHistory temp = (TimeHistory)Tools.deserialize(buffer);
						    		list.add(temp);	
		    				}
		    				break;
		    			case "s":
		    				s = file.getName().split("\\.");
		    				if (s[1].equals("spc")){
									buffer = Files.readAllBytes(new File(file.getName()).toPath());
						    		Spectrum temp = (Spectrum)Tools.deserialize(buffer);
						    		list.add(temp);	
		    				}
		    			}
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
		    	}
		    }
		}
		return list;
	} 
	
	void loop(List listTH, List listS) {
		while(true) {
			try {
				String line;
				String type;
				System.out.println("\nWybierz operację: \n[a] zapisz obiekt do listy \n[b] wyszukaj pozycję na liście \n[c] zapisz obiekt z listy do katalogu serwera \n[d] wyświetl zawartość obiektu z listy \n[e] zakończ ");
				if(userInput.hasNextLine()) {
					line = userInput.next();
					switch (line) {
						case "a":
							System.out.println("Wybierz typ obiektu: \n[t] TimeHistory \n[s] Spectrum");
							type = userInput.next();
							switch(type) {
								case "t":
									listTH = zapiszDoListy(listTH, type);
									break;
								case "s":
									listS = zapiszDoListy(listS, type);
									break;
								default:
									System.out.println("Zła komenda");
							}
							break;
						case "b":
							zobaczListe(listTH, listS);
							break;
						case "c":
							System.out.println("Wybierz typ obiektu: \n[t] TimeHistory \n[s] Spectrum");
							type = userInput.next();
							switch(type) {
								case "t":
									zapiszDoKatalogu(listTH, type);
									break;
								case "s":
									zapiszDoKatalogu(listS, type);
									break;
								default:
									System.out.println("Zła komenda");
							}
							break;	
						case "d":
							System.out.println("Którą listę chcesz przeszukać? \n[t] listę obiektów typu TimeHistory \n[s] listę obiektów typu Spectrum");
							type = userInput.next();
							switch(type) {
								case "t":
									wyswietlZListy(listTH, type);
									break;
								case "s":
									wyswietlZListy(listS, type);
									break;
								default:
									System.out.println("Zła komenda");
							}
							break;
						case "e":
							return;
						default:
							System.out.println("Zła komenda");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}