package com.nsb.practice.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server
 * @author Dorae
 *
 */
public class IOServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8080);
		// 接受连接线程
		new Thread(() -> {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					new Thread(() -> {
						try {
							byte[] data = new byte[1024];
							InputStream inputStream = socket.getInputStream();
							while (true) {
								int len = inputStream.read(data);
								while (len != -1) {
									System.out.println(new String(data, 0, len));
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}).start();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
}
