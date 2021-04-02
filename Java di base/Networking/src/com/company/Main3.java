package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Main3 {

    public static void main(String[] args) {

        try{

            URL url = new URL("http://example.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            /*L'operazione GET è quella che implicitamente facciamo quando digitiamo un link
            sul motore di ricerca. Il browser manderà una richiesta al server che ospita il sito
            che ci interessa e questa operazione è appunto detta GET.
            Non sarebbe nemmeno necessario specificarlo perchè è l'operazione di default
             */
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setReadTimeout(30000);

            int responseCode = connection.getResponseCode(); //questo fa anche da connessione
            System.out.println("Response code: " + responseCode);

            if(responseCode != 200){ //200 è il codice di OK, come 404 è il famoso codice di errore
                System.out.println("Error reading web page");
                System.out.println(connection.getResponseMessage());
                //getResponseMessage dice "Not Found" in caso di 404
            }



            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream() /*oppure url.openStream()*/));

            String line;
            while((line = inputReader.readLine()) != null){
                System.out.println(line);
            }
            inputReader.close();

        }catch(IOException e){
            e.getMessage();
        }

    }
}
