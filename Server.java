import java.io.*;
import java.net.*;
import java.util.zip.CRC32;

public class Server {

	private static Socket socket;
	public static void main(String[] args){
		try{
			int port= 1999;
			ServerSocket serverSocket= new ServerSocket(port);
			
			while(true){
				socket = serverSocket.accept();
				System.out.println("Accepting message from client.");
				InputStream is= socket.getInputStream();
				System.out.println("Done 1");
				InputStreamReader isr= new InputStreamReader(is);
				System.out.println("Done 2");
				BufferedReader br= new BufferedReader(isr);
				System.out.println("Done 3");
				String filename= br.readLine();
				System.out.println("Messege sent is: " +filename);
				
				File newFile= new File(filename);
				if(newFile.exists()==true){
				
					ComputeChecksum checksum= new ComputeChecksum();
					CRC32 checkValue = new CRC32();
					long sentCheck = checksum.getChecksum(checkValue, filename);
				
					OutputStream os= socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					BufferedWriter bw= new BufferedWriter(osw);
					String returnMessage= "File: "+filename+"exists. Checksum is: "+sentCheck+"\n";
					bw.write(returnMessage);
					//System.out.println(returnMessage);
					System.out.println("ACK message sent to client.");
					bw.flush();
					
					
					BufferedReader file= new BufferedReader(new FileReader(filename));
					byte []b= new byte[1000];
					String k= file.readLine();
					System.out.println(k);
					DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
					dos.writeUTF(k);
					dos.flush();
					System.out.println(filename+" File Transferred from Server");
					file.close();
					
				
				}else{
					OutputStream os= socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					BufferedWriter bw= new BufferedWriter(osw);
					String returnMessage= "File: "+filename+"does not exist.";
					bw.write(returnMessage);
					System.out.println("Negative ACK message sent to client.");
					bw.flush();
				}
				
			} //end while-loop
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
