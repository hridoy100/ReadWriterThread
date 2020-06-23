package io.github.hridoy100;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class WriteThread implements Runnable{
    ObjectOutputStream oos;
    Thread t;
    WriteThread(Socket socket){
        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.t = new Thread(this);
        this.t.start();
    }

    void write(Object o){
        try {
            this.oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("WriterThread started..");
//        Scanner sc = new Scanner(System.in);
        while (true){
            String message = "sc.nextLine()";
            if(message.equals("exit")){
                break;
            }
            write(message);
        }
        try {
            this.oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
