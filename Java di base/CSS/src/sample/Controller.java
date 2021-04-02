package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label label;

    @FXML
    private Button button4;

    @FXML
    private WebView webView;

    public void initialize(){
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter(){
        label.setScaleY(2.0);
        label.setScaleY(2.0);
    }

    @FXML
    public void handleClick(){
        /**FileChooser chooser = new FileChooser();
        chooser.showOpenDialog(gridPane.getScene().getWindow()); */
        /*mettendo come argomento gridPane.getScene().getWindow() succede che cliccando su Open...
        si apre la finestra di ricerca File ma non è consentito agire sulla finestra del programma.
        Invece, mettendo come argomento null si consente di agirvi, inoltre cliccando ancora su Open...
        si aprono nuove finestre di ricerca File
         */

        //il seguente frammento permette di selezionare dal File Folder solo file del tipo specificato,
        //in questo caso file txt e pdf

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Application File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.gif", "*.png"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());

        //DirectoryChooser chooser = new DirectoryChooser();

        if(file != null)
            System.out.println(file.getPath());
        else
            System.out.println("Chooser was cancelled");

        /**oppure si può anche fare in modo di selezionare più file:
         *
         * List<File> files = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
         * if(files != null){
         * for(int i=0; i<files.size(); i++)
         * System.out.println(files.get(i);
         */

    }

    @FXML
    public void handleMouseExit(){
        label.setScaleY(1.0);
        label.setScaleX(1.0);
    }

    @FXML
    public void handleLinkClicked(){
        /*ALTERNATIVA per aprire la pagina esternamente
        try{
            Desktop.getDesktop().browse(new URI("https://www.javafx.com"));
        }catch(IOException e){
            e.printStackTrace();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }*/

        //la seguente invece apre la pagina all'interno della finestra

        WebEngine engine = webView.getEngine();
        engine.load("http://www.javafx.com");
    }

}
