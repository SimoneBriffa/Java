package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button clickMeButton;

    //Versione con Lambda. Possiamo usarla perchè EventHandler è un'interfaccia funzionale,
    //cioè ha da definire un sole metodo, cioè handle()

    public void initialize(){
        clickMeButton.setOnAction(event -> System.out.println("You Clicked Me"));
    }

    /*
    VERSIONE CLASSICA

    public void initialize1(){
        clickMeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("You clicked me");
            }
        });

    } */

}
