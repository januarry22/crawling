package com.kb.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;

public class FTPClient {

	
	 
    private String serverIP;
    private String user;
    private String passwd;
    private String defaultPath;

    
    
    public FTPClient() {
    }
    
    public FTPClient(String serverIP, String user, String passwd, String defaultPath) {
        this.serverIP = serverIP;
        this.user = user;
        this.passwd = passwd;
        this.defaultPath = defaultPath;
    }
    
    public void init(Properties p) {
    	serverIP = p.getProperty("server_ip");
        user = p.getProperty("user_name");
        passwd = p.getProperty("password");
        defaultPath = p.getProperty("default_path");
    }
    
    
    /**
     * 파일 업로드
     * @param org 원본파일
     * @param targetFile 저장할 파일위치/파일명
     * @throws IOException 
     * @throws SocketException 
     */
    public boolean upload(File org, String targetFile)
            throws SocketException, IOException, Exception {
        
        FileInputStream fis = null;
        
        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("utf-8");
        
        try {
            clnt.connect(serverIP);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }
            
            clnt.setSoTimeout(1000 * 10);
            clnt.login(user, passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);
            
            
            clnt.enterLocalActiveMode();
            
            fis = new FileInputStream(org);
            return clnt.storeFile(targetFile, fis);
        } finally {
            if (clnt.isConnected()) {
                clnt.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

}
