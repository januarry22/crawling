package com.kb.api.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * @version : java1.8
 * @author : ohs
 * @date : 2018. 8. 8.
 * @class :
 * @message : API 서버 공통 소스
 * @constructors :
 * @method :
 */
@Component
public class Common {

    public AWSS3Client awsS3 = AWSS3Client.getInstance();
	//String fileSavePath = "F:\\ohs\\PROJECT\\PROJECT\\goodbaby\\src\\main\\webapp\\resources\\images\\";
	
	String fileSavePath ="/opt/tomcat/webapps/images/";
	//String fileSavePath = "/Users/sinjiwon/Desktop/owner/";
	/**
	 * 파일다운로드
	 * @param URL
	 * @param filename
	 */
    public void download(final String URL, final String filename,final int timeOut) {
        Thread thread = new Thread(new Runnable() {
            public synchronized void run() {
//                System.out.println( "[파일업로드 시작] "+ Thread.currentThread().getName());
//                System.out.println( "[파일업로드 URL] "+ URL);
                sslTrustAllCerts();
                Connection conn = Jsoup.connect(URL);
                conn.maxBodySize(0);
                conn.ignoreContentType(true);
                conn.timeout(timeOut);
                conn.ignoreHttpErrors(true);

                try {
                    Response response = conn.execute();

                    File saveFile = new File( fileSavePath+filename);
                    FileOutputStream out = new FileOutputStream(saveFile);
                    out.write( response.bodyAsBytes());
                    out.close();
                   upload(saveFile,filename); 

                    if(saveFile.exists()){
                        if(saveFile.delete()){
                            System.out.println( "[파일 삭제 성공]");
                        }else{
                            System.out.println( "[파일 삭제 실패]");
                        }
                    }else{
                        System.out.println( "[파일 없음]");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
//                    System.out.println( "[파일업로드 종료]"+ Thread.currentThread().getName());
                }
            }
        }, "FILE_UPLOAD");
        thread.start();
    }


    public void sslTrustAllCerts(){
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
        };

        SSLContext sc;

        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    public void upload(File file, String key) {
        awsS3.upload(file, key);
    }

    public void copy(String orgKey, String copyKey) {
        awsS3.copy(orgKey, copyKey);
    }

    public void delete(String key) {
        awsS3.delete(key);
    }

    public static void mkDir(String realPath) {
        File updir = new File(realPath);
        if (!updir.exists()) {
            updir.mkdirs();
        }
    }
    
	static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	public static String GmakeDynamicValueObject(ResponseEntity<Map<String, Object>> entity){
		String ss = "";
		ss += gson.toJson(entity);
		return ss;
	}
	public static void returnPrint(String str){
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(str);
		str = gson.toJson(je);
		
		System.out.println("--------------------      Response Param     ---------------------------");
		System.out.println(str);
		System.out.println("------------------------------------------------------------------------");
	}
	
}
