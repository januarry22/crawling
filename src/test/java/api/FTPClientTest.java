package api;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import org.junit.Test;

import com.kb.api.util.FTPClient;


public class FTPClientTest {

	
	
	

	@Test
	public void ftpTest() throws SocketException, IOException, Exception {
		//파일 선언   
		File target =null;
		 
		
		FTPClient ftpCon = new FTPClient("3.38.103.203", "ubuntu", "FTP비밀번호", "");                
		boolean result = ftpCon.upload(target, "파일 저장 경로");
		System.out.println("FTP result : " + result);
	}
	

}
