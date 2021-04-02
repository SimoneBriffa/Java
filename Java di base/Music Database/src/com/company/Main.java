package com.company;

import model.Artist;
import model.Datasource;
import model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        if(datasource.open()){
        /*
            for(String album: datasource.queryAlbumsFromArtist("Pink Floyd", Datasource.ORDER_BY_ASC)){
                System.out.println(album);
            }

            List<SongArtist> songArtists = datasource.queryArtistForSong("She's On Fire", Datasource.ORDER_BY_ASC);
            if(songArtists == null){
                System.out.println("Couldn't find the artist for the song");
                return;
            }

            for(SongArtist artist: songArtists){
                System.out.println("Artist name: " + artist.getArtistName() +
                        " - Album name: " + artist.getAlbumName() + " - Track: " + artist.getTrack());
            }*/

            datasource.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);

        }

        datasource.close();

    }
}
