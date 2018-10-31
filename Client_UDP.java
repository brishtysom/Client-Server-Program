import java.net.*;
import java.io.*;

public class Client_UDP {
	
	
	public static void main(String[] args) throws Exception{
		
		String filepath= "C:/Users/brishty.som/Documents/Networks_Proj/UDP_TestFile.txt";
		int port= 25000;
		String host= "localhost";
		DatagramSocket dsock= new DatagramSocket();
		InetAddress address = InetAddress.getByName(host);
		
		
		byte[] data= filepath.getBytes();
		DatagramPacket sendRequest = new DatagramPacket(data, data.length,address,port);
		dsock.send(sendRequest);
		System.out.println("Request sent from client");
		
		
		//File file= new File(filename);
		//byte[] incomingData= new byte[1024];
		

	}

}
