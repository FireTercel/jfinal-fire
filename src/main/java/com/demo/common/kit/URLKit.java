package com.demo.common.kit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.junit.Test;

public class URLKit {

	public void urlTest() throws Exception {
		URL url = new URL(
				"http://www.yahoo.com:80/en/index.html?name=john#first");
		ClassKit.println("protocol:" + url.getProtocol());
		ClassKit.println("port:" + url.getPort());
		ClassKit.println("host:" + url.getHost());
		ClassKit.println("path:" + url.getPath());
		ClassKit.println("file:" + url.getFile());
		ClassKit.println("query:" + url.getQuery());
		ClassKit.println("ref:" + url.getRef());

	}

	@Test
	public void loadWebSources() {
		try {
			URL url = new URL("http://www.baidu.com");
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();
			while (line != null) {
				ClassKit.println(line);
				line = br.readLine();
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		String host = "books.brainysoftware.com";
		String protocol = "http";
		try {
			Socket socket = new Socket(protocol + "://" + host, 80);
			OutputStream os = socket.getOutputStream();
			boolean autoflush = true;
			PrintWriter out = new PrintWriter(os,
					autoflush);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out.println("GET / HTTP/1.1");
			out.println("Host: " + host + ":80");
			out.println("Connection: Close");
			out.println();

			boolean loop = true;
			StringBuilder sb = new StringBuilder(8096);
			while (loop) {
				if (in.ready()) {
					int i = 0;
					while (i != -1) {
						i = in.read();
						sb.append((char) i);
					}
					loop = false;
				}
			}
			ClassKit.println(sb.toString());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
