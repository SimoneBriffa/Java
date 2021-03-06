package model;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Simone Briffa\\Desktop\\Java Udemy\\Databases\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS + " INNER JOIN "
                    + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " WHERE " +
                    TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + "=" + "\"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    /*CREATE VIEW IF NOT EXISTS artist_list AS SELECT artists.name, albums.name AS album,
    songs.track, songs.title FROM songs INNER JOIN albums ON songs.album = albums._id
    INNER JOIN artists ON albums.artist = artist._id ORDER BY artists.name, albums.name, song.track
     */

    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS +
            "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    public static final String QUERY_VIEW_SONG_INFO =  "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";


    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";

    /* SELECT name, album, track FROM artist_list WHERE title = ?
    il punto interrogativo ?? una funzionalit?? chiave per quanto riguarda la protezione;
    far?? in modo che ci?? che viene aggiunto alla stringona di comando sql, eventualmente da tastiera,
    non venga concatenata blandamente ma con un meccanismo di protezione, in modo che un eventuale hacker, pur
    digitando il codice hack per far venire fuori tutti i dati, non avr?? successo
    */

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            '(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    private Connection conn;

    private PreparedStatement querySongInfoView; //classe per la protezione
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            //querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database. " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if(querySongInfoView != null){
                querySongInfoView.close();
            }

            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }

            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if(insertIntoSongs != null) {
                insertIntoSongs.close();
            }

            if(queryAlbum != null) {
                queryAlbum.close();
            }

            if(queryArtist != null) {
                queryArtist.close();
            }

            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Artist> queryArtist(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }


        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Query Failed. " + e.getMessage());
            e.printStackTrace();
            return null;

        }
    }

    public List<String> queryAlbumsFromArtist(String artistName, int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        System.out.println("SQL statement = " + sb.toString()); //stampa il comando a SQL

        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(results.getString(1)); //1 ?? l'indice della colonna(in questo caso ce ne sar?? solo una
                //quindi possiamo semplificare mettendo 1
            }
            return albums;


        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    /*
        VERSIONE SENZA TRY WITH RECOURCES CON BLOCCHI FINALLY

    public List<Artist> queryArtist() {
        Statement statement = null;
        ResultSet results = null;

        try {

            statement = conn.createStatement();
            results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Query Failed. " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (results != null)
                    results.close();
            } catch (SQLException e) {
                System.out.println("Error closing results. " + e.getMessage());
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Couldn't close. " + e.getMessage());
                e.printStackTrace();
            }

        }
        return null;
    }*/

    public List<SongArtist> queryArtistForSong(String songName, int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        System.out.println("SQL Statement: " + sb.toString());

        try(Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())){

            List<SongArtist> songArtists = new ArrayList<>();

            while(results.next()){
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);

            }

            return songArtists;

        }catch(SQLException e){
            System.out.println("Query Failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    //la seguente funzione serve per fare ci?? che in sql facciamo con .schema

    public void querySongsMetadata(){

        String sql = "SELECT * FROM " + TABLE_SONGS;

        try(Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)){

            ResultSetMetaData meta = results.getMetaData();
            int numColomns = meta.getColumnCount();
            for(int i=1; i<numColomns; i++){
                System.out.format("Column %d in the songs table is named %s\n", i, meta.getColumnName(i));
            }

        }catch(SQLException e){
            System.out.println("Query Failed: " + e.getMessage());
        }

    }

    public int getCount(String table){
        String sql = "SELECT COUNT(*) AS count FROM " + table;
        try(Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)){

            //?? buona norma dare un nome al conteggio delle righe
            int count = results.getInt("count");

            /*
            Se in sql non avessimo messo AS count allora la scrittura sarebbe stata la seguente
            int count = results.getInt(1);
            poco importa quale colonna sia, qualsiasi colonna, sommate le proprie righe, ci da il risultato che ci interessa
             */
            return count;

        }catch(SQLException e){
            System.out.println("Query failed" + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    //Creare una VIEW con Java non ?? niente di diverso dal crearla con la command line

    public boolean createViewForSongArtists(){

        try(Statement statement = conn.createStatement()){

            statement.executeQuery(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;

        }catch(SQLException e){
            System.out.println("Query failed" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public List<SongArtist> querySongInfoView(String title){

        try{
            querySongInfoView.setString(1, title); //l'indice 1 si riferisce al primo punto interrogativo
            ResultSet results = querySongInfoView.executeQuery(); //usando le PreparedStatement, in executeQuery non vanno parametri

                List<SongArtist> songArtists = new ArrayList<SongArtist>();
                while(results.next()) {
                    SongArtist songArtist = new SongArtist();
                    songArtist.setArtistName(results.getString(1));
                    songArtist.setAlbumName(results.getString(2));
                    songArtist.setTrack(results.getInt(3));
                    songArtists.add(songArtist);
                }
                return songArtists;
            }catch(SQLException e){
                System.out.println("Query failed: " + e.getMessage());
                e.printStackTrace();
                return null;
            } finally {
                    close();
                }
            }

        /*try(Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())){

            List<SongArtist> songArtists = new ArrayList<SongArtist>();
            while(results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        }catch(SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }*/


    public int insertArtists(String name) throws SQLException {

        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();
        if(results.next()) { //in questo caso significa che l'artista ?? gi?? presente nel database
            return results.getInt(1); //ci ritorna _id di artist
        }else{
            //inseriamo l'artista
            insertIntoArtists.setString(1, name);
            int affectedRows = insertIntoArtists.executeUpdate();

            if(affectedRows != 1){ //se ritorna 0 vuol dire che non ha aggiunto righe, cio?? ha fallito
                throw new SQLException("Couldn't insert artist");
            }

            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            } else throw new SQLException("Couldn't get _id for artist");

    }

}

    public int insertAlbum(String name, int artistId) throws SQLException {

        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();
        if(results.next()) { //in questo caso significa che l'album ?? gi?? presente nel database
            return results.getInt(1); //ci ritorna _id di artist
        }else{
            //inseriamo l'album
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if(affectedRows != 1){ //se ritorna 0 vuol dire che non ha aggiunto righe, cio?? ha fallito
                throw new SQLException("Couldn't insert album");
            }

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            } else throw new SQLException("Couldn't get _id for album");

        }

    }

    public void insertSong(String title, String artist, String album, int track) {

        try {
            conn.setAutoCommit(false); //se mettiamo autoCommit su true, il rollback non potr?? essere eseguito
            //quindi in caso di errore sarebbe un problema

            int artistId = insertArtists(artist);
            int albumId = insertAlbum(album, artistId);
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);

            int affectedRows = insertIntoSongs.executeUpdate();

            if (affectedRows == 1) {
                conn.commit();
            } else throw new SQLException("The song insert failed");

        } catch (SQLException e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Cazzi amari: " + e.getMessage());
            } finally {
                try {
                    System.out.println("Resetting default commit behavior");
                    conn.setAutoCommit(true);
                } catch (SQLException e3) {
                    System.out.println("Couldn't reset auto-commit " + e3.getMessage());
                    e3.printStackTrace();
                }
            }

        }

    }

}

