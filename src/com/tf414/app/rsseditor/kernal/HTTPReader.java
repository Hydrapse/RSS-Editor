package com.tf414.app.rsseditor.kernal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HTTPReader {
	
//	/**
//	 * 
//	 * @param urlstr 网页地址
//	 * @throws IOException
//	 * @return result
//	 */
//	public static String read(String url) throws IOException{
//		BufferedReader bReader = null;
//		InputStreamReader myReader = null;
//		StringBuffer sbuf =new StringBuffer();
//		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
//        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//		myReader = new InputStreamReader(conn.getInputStream(), "utf-8");
//		bReader = new BufferedReader(myReader);
//		String s = null;
//		while((s = bReader.readLine()) != null ) {
//			sbuf.append(s);
//		}
//		bReader.close();
//		myReader.close();
//		return sbuf.toString();
//	}
	
	/**
	 * 
	 * @param urlstr 网页地址
	 * @throws IOException
	 * @return result
	 */
	public static InputStream read(String url) throws IOException{
		StringBuffer sbuf =new StringBuffer();
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		return conn.getInputStream();
	}
}
