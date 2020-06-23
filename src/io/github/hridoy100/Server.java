package io.github.hridoy100;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket ServSock;
	
	Server() {
		try {
			ServSock = new ServerSocket(33333);
			while (true) {
				ServerThread m = new ServerThread(ServSock.accept());
				System.out.println("Client Connected...");
				Thread.sleep(1000);
			}
		}catch(Exception e) {
			System.out.println("Server starts:"+e);
		}
	}
	
	public static void main(String args[]) {
		Server objServer = new Server();
	}
}

class ServerThread implements Runnable {
	private Socket ClientSock;
	private Thread thr;

	ServerThread(Socket client) {
		this.ClientSock = client;
		this.thr = new Thread(this);
		thr.start();
	}

	public void run() {
		System.out.println("ServerThread Started...");
		synchronized (this){
			new ReadThread(this.ClientSock);
			new WriteThread(this.ClientSock);
		}
	}
}

