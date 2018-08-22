package networking.udp; 

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekt.Packet;
import projekt.TimeHistory;

public class UDPClient{
	public static void main(String[] args) {
		DatagramSocket aSocket = null;
		try {
			byte[] buffer = new byte[1024];
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 9876;
			aSocket = new DatagramSocket();
				
			Integer[] p = new Integer[1];
			p[0] = 10;
				
			TimeHistory<Integer> packet1 = new TimeHistory<Integer>("Dev1", "Desc1", 2121, 1, "Volt", 6.56, p, 7.77);
			byte[] data1 = Tools.serialize(packet1);
			DatagramPacket datpack1 = new DatagramPacket(data1, data1.length,aHost,serverPort);
			aSocket.send(datpack1);				

			TimeHistory<Integer> packet2 = new TimeHistory<Integer>("Dev1", "Desc2", 3232, 2, "Volt", 6.56, p, 7.77);
			byte[] data2 = Tools.serialize(packet2);
			DatagramPacket datpack2 = new DatagramPacket(data2, data2.length,aHost,serverPort);
			aSocket.send(datpack2);
				
			TimeHistory<Integer> packet3 = new TimeHistory<Integer>("Dev2", "Desc3", 4343, 1, "Volt", 6.56, p, 7.77);
			byte[] data3 = Tools.serialize(packet3);
			DatagramPacket datpack3 = new DatagramPacket(data3, data3.length,aHost,serverPort);
			aSocket.send(datpack3);
				
			TimeHistory<Integer> packet4 = new TimeHistory<Integer>("Dev2", "Desc4", 5454, 2, "Volt", 6.56, p, 7.77);
			byte[] data4 = Tools.serialize(packet4);
			DatagramPacket datpack4 = new DatagramPacket(data4, data4.length,aHost,serverPort);
			aSocket.send(datpack4);
				
			Request req1 = new Request("Dev1","2");
			byte[] data5 = Tools.serialize(req1);
			DatagramPacket datpack5 = new DatagramPacket(data5, data5.length,aHost,serverPort);
			aSocket.send(datpack5);
				
			Request req2 = new Request("-","-");
			byte[] data6 = Tools.serialize(req2);
			DatagramPacket datpack6 = new DatagramPacket(data6, data6.length,aHost,serverPort);
			aSocket.send(datpack6);
			
			Request req3 = new Request("-","1");
			byte[] data7 = Tools.serialize(req3);
			DatagramPacket datpack7 = new DatagramPacket(data7, data7.length,aHost,serverPort);
			aSocket.send(datpack7);
			
			Request req4 = new Request("Dev3","1");
			byte[] data8 = Tools.serialize(req4);
			DatagramPacket datpack8 = new DatagramPacket(data8, data8.length,aHost,serverPort);
			aSocket.send(datpack8);
				
			while(true) {
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);
				Packet read = (Packet)Tools.deserialize(reply.getData());
				if(read instanceof Request) {
				} else if (read instanceof FileList){
					System.out.println("New request:\n" + read.getFileName() + "\n");
					System.out.println(read.toString()+ "\n");
				} else {
					System.out.println("Presenting data from requested file:");
					System.out.println(read.toString()+ "\n");
				}
			}
		} catch (SocketException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			aSocket.close();
		}
	}
}