package com.company;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Simone Briffa\\Desktop\\Java Udemy\\Databases\\"+DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

        try{
            Connection connection = DriverManager.getConnection(Main.CONNECTION_STRING);
            connection.setAutoCommit(true);
            //questa funzione setAutoCommit, se impostata a false, impedisce l'aggiunta di nuovi elementi;
            //se scriviamo il codice per inserire un nuovo elemento al database, il compilatore lo eseguirà ma nel database
            //non comparirà. Impostarlo a true è come non dichiararla

            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                              " (" + COLUMN_NAME + " text, " + COLUMN_PHONE + " integer, " +
                              COLUMN_EMAIL + " text)");

            insertContact(statement, "Tim", 6543, "tim@gmail.com");
            insertContact(statement, "Joe", 1234, "joe@email.com");
            

          /*  statement.execute("INSERT INTO contacts (name, phone, email) VALUES('Tim', 123456, 'tim@mail.com')");
            statement.execute("DELETE FROM contacts WHERE name='Tim'"); //non serve l'asterisco*/


            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while(result.next()){
                System.out.println(result.getString(COLUMN_NAME) + " " +
                        result.getString(COLUMN_PHONE) + " " +
                        result.getString(COLUMN_EMAIL));
            }

            result.close();

            //le seguenti dichiarazioni sono superflue se si lavora con try with resources
            //vanno messe tra parentesi le dichiarazioni di connection e di statement

            statement.close();
            connection.close();

        }catch(SQLException e){
            System.out.println("Qualcosa è andato storto" + e.getMessage());
        }

    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " +
                COLUMN_EMAIL + ")" +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}
