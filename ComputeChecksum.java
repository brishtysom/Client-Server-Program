import java.io.*;
import java.util.zip.Checksum;

public class ComputeChecksum {

	public long getChecksum(Checksum chksum, String file ){
		try{
			FileInputStream filename= new FileInputStream(file);
			BufferedInputStream is= new BufferedInputStream(filename);
			byte[] b= new byte[1024];
			int len=0;
			
			while((len= is.read(b)) >= 0){
				chksum.update(b, 0, len);
			}
			is.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		return chksum.getValue();
	}
}
