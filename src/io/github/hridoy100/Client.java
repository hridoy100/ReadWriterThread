package io.github.hridoy100;

import java.net.Socket;

public class Client
{
	public static void main(String args[]) {
		try {
            String serverAddress="127.0.0.1";
            int serverPort=33333;
			Socket socket = new Socket(serverAddress, serverPort);
			new ReadThread(socket);
			new WriteThread(socket);
		} catch(Exception e) {
			System.out.println (e);
		}

	}
}

