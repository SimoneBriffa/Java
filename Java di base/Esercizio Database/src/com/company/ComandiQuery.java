package com.company;

import java.awt.*;
import java.io.File;

public enum ComandiQuery { ;

    public static final String DB_NAME = "music.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:C:" +
            File.separator + "Users" + File.separator + "Simone Briffa" +
            File.separator + "Desktop" + File.separator + "Java Udemy" +
            File.separator + "Databases" + File.separator + DB_NAME;

    public static final String DOWNLOAD_SONGS = "SELECT * FROM songs ORDER BY title";

    public static final String SEARCH_SONG = "SELECT * FROM songs WHERE title = ?";

}


