package com.company;

import javax.xml.transform.Result;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Funzionalità {

    Connection connettore;

    public boolean open(){

        try{
            connettore = DriverManager.getConnection(ComandiQuery.CONNECTION_STRING);
            return true;

        }catch(SQLException e){
            e.getMessage();
            e.printStackTrace();
            return false;
        }


        }

    public List<Canzone> scaricaCanzoni(){

        Path fileCanzoni = FileSystems.getDefault().getPath("canzone.dat");
        List<Canzone> canzoni = new ArrayList<>();

            try(BufferedWriter bw = Files.newBufferedWriter(fileCanzoni);
                Statement statement = connettore.createStatement();
                ResultSet results = statement.executeQuery(ComandiQuery.DOWNLOAD_SONGS)){

            while (results.next()) {
                Canzone canzone = new Canzone();
                canzone.setId(results.getInt(1));
                canzone.setAlbumId(results.getInt(2));
                canzone.setName(results.getString(3));
                canzone.setAlbumId(results.getInt(4));
                canzoni.add(canzone);
                bw.write(canzone.toString()+"\n");
            }

        }catch(SQLException | IOException e){
            e.getMessage();
            e.printStackTrace();
        }

        return canzoni;
    }

    public void cercaCanzone(String titolo){

        try(PreparedStatement querySong = connettore.prepareStatement(ComandiQuery.SEARCH_SONG)){

            querySong.setString(1, titolo);
            ResultSet results = querySong.executeQuery();
            if(results != null){
                Canzone canzone = new Canzone();
                canzone.setId(results.getInt(1));
                canzone.setAlbumId(results.getInt(2));
                canzone.setName(results.getString(3));
                canzone.setAlbumId(results.getInt(4));
                System.out.println(canzone.toString());
            }

        }catch(SQLException e){
            e.getMessage();
            e.printStackTrace();
        }


    }

}
