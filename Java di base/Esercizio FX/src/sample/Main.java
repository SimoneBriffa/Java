package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.datamodel.ToDoData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ToDo List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /*la seguente funzione entra in scena quando l'utente spegne l'applicazione, in modo
    da salvare i dati che ha immesso
     */
    @Override
    public void stop() throws Exception {
        try {
            ToDoData.getInstance().storeTodoItems();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*la seguente funzione serve invece a caricare i file al momento dell'avvio*/
    @Override
    public void init() throws Exception {
        try {
            ToDoData.getInstance().loadToDoItems();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

