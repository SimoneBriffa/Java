package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    //Usiamo il protocollo UDP quando necessitiamo di avere una risposta dal server

    public static void main(String[] args) {

        try{
            DatagramSocket socket = new DatagramSocket(5000);

            while(true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Text received is: " + new String(buffer));

                //il seguente blocco rimanda indietro al client quando ricevuto
                String returnString = "echo: " + new String(buffer);
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);
            }

        } catch(IOException e){
            e.getMessage();
            e.printStackTrace();
        }

    }
}
