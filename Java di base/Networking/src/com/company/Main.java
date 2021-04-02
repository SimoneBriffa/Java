package com.company;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        //Qui vediamo come accedere ad internet
        //Un URI può specificare un percorso relativo, mentre un URL specifica un percorso assoluto
        //http è uno "schema"

        try {
            /*URI uri = new URI("db://username:password@myserver.com:5000//catalogue//phones?os=android#samsung");

            URL url = uri.toURL(); questo lancerà un'eccezione perchè il percorso specificato ad URI
            non è valido. Finchè si tratta di lavorare solo con URI allora può anche non essere valido,
            ma se dobbiamo convertirlo in URL allora deve esserlo   */

            URI uri = new URI("http://username:password@myserver.com:5000//catalogue//phones?os=android#samsung");

            URL url = uri.toURL(); //questo funziona

            System.out.println(url);

            //data una URI si può anche usare la funzione .resolve("altra URI") per agganciare
            //la restante parte di URI

            /*System.out.println("Scheme = " + uri.getScheme());
            System.out.println("Scheme-specific part = " + uri.getSchemeSpecificPart());
            System.out.println("Authority = " + uri.getAuthority());
            System.out.println("User info = " + uri.getHost());*/



        } catch (URISyntaxException | MalformedURLException e) {
            e.getMessage();
        }
    }

}
