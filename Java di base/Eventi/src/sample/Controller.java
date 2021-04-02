package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private Label ourLabel;
    @FXML
    private CheckBox ourCheckBox;

    @FXML
    public void initialize(){
    helloButton.setDisable(true);
    byeButton.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if (e.getSource().equals(helloButton))
            System.out.println("Hello, " + nameField.getText());
        else if (e.getSource().equals(byeButton))
            System.out.println("Bye, " + nameField.getText());

        /*il seguente oggetto crea un processo in background che lavora in parallelo*/

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    //quanto segue è un esempio di debugging
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the: " + s);
                    /*prova a stoppare il programma per 10 secondi. Può essere utile per impedire all'utente di fare
              danni quando non dovrebbe*/
                    Thread.sleep(10000);
                    /*quanto segue serve a non far crashare il programma dopo 10 secondi, perchè a quel punto
                    interviene un altro thread */
                    Platform.runLater(new Runnable() {
                                          @Override
                                          public void run() {
                                              ourLabel.setText("We did something");
                                          }
                                      });
                } catch (InterruptedException e) { /*sticazzi*/ }
                }
            };

        new Thread(task).start(); //avvia un nuovo processo

        if(ourCheckBox.isSelected()) {
                nameField.clear();
                helloButton.setDisable(true);
                byeButton.setDisable(true);
            }
        }


    @FXML
    public void handleKeyReleased(){
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        //cosa fa trim()? tipo s = "    ciao mbare   " -> s.trim() = "ciao mbare"
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    public void handleChange(){
        System.out.println("The checkbox is " + ((ourCheckBox).isSelected() ? "checked " : "not checked"));
    }

}
