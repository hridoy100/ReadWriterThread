package io.github.hridoy100;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReadThread implements Runnable{
    ObjectInputStream ois;
    Thread t;
    ReadThread(Socket socket){
        try {
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.t = new Thread(this);
        this.t.start();
    }

    Object read(){
        Object message = null;
        try {
            message = this.ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public void run() {
        System.out.println("ReaderThread Started");
        while (true){
            String msg = (String)read();
            if(msg==null)
                break;
            System.out.println(msg);
        }
        try {
            this.ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
