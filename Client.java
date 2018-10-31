import java.net.*;
import java.io.*;

public class Client {

	private static Socket socket;
	public static void main(String[] args) {
		try{
			String host= "localhost";//fe80::843d:6be2:d887:4e1b%11";
			int port= 1999;
			String filename= "Message.txt";
			InetAddress address= InetAddress.getByName(host);
			socket= new Socket(address, port);
			OutputStream os= socket.getOutputStream();
			OutputStreamWriter osw= new OutputStreamWriter(os);
			BufferedWriter bw= new BufferedWriter(osw);
			
			//String request= "Send me the file: " +filename;
			String sendMessage= filename+ "\n";
			bw.write(sendMessage);
			bw.flush();
			System.out.println(sendMessage);
			System.out.println("Message sent to server");
			
			 InputStream is = socket.getInputStream();
	         InputStreamReader isr = new InputStreamReader(is);
	         BufferedReader br = new BufferedReader(isr);
	         String message = br.readLine();
	         System.out.println(message);
			
			DataInputStream dis= new DataInputStream(socket.getInputStream());
			String k= dis.readUTF();
			System.out.println(k);
			System.out.println("File accepted by Client.");
			FileOutputStream fos= new FileOutputStream("receivedFile.txt");
			byte[] b= k.getBytes();
			fos.write(b);
			System.out.println("File saved as receivedFile.txt in same folder.");
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
