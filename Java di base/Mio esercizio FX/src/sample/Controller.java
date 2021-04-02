package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ListView<Amico> listaAmiciStampata;

    @FXML
    private ObservableList<Amico> listaAmici;

    @FXML
    private TextField campoNuovoNome;

    @FXML
    private TextField campoNuovoCognome;

    @FXML
    private TextField campoNuovoLavoro;

    @FXML
    private VBox boxNuovoAmico;

    @FXML
    private Button conferma;

    @FXML
    private Button annulla;


    public void initialize(){

        FXMLLoader loader = new FXMLLoader();
        loader.getClass().getResource("sample.fxml");

        listaAmici = FXCollections.observableArrayList();

        Path percorsoAmici = FileSystems.getDefault().getPath("file_amici.txt");

        try{
            for(String amico: Files.readAllLines(percorsoAmici)){
                String[] campiDiAmico = amico.split(",");
                listaAmici.add(new Amico(campiDiAmico[0], campiDiAmico[1], campiDiAmico[2]));
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void visualizzaAmici() {
        listaAmiciStampata.setItems(listaAmici);
    }

    @FXML
    public void aggiungiNuovoAmico(){

        boxNuovoAmico.setVisible(true); 

        conferma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String nome = campoNuovoNome.getText();
                String cognome = campoNuovoCognome.getText();
                String lavoro = campoNuovoLavoro.getText();
                Amico amico = new Amico(nome, cognome, lavoro);
                listaAmici.add(amico);
            }
        });

        annulla.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                campoNuovoCognome.clear();
                campoNuovoLavoro.clear();
                campoNuovoNome.clear();
            }
        });

    }

    @FXML
    public void salvaAmici(){

        Path percorsoAmico = FileSystems.getDefault().getPath("file_amici.txt");

        try(BufferedWriter bw = new BufferedWriter(Files.newBufferedWriter(percorsoAmico))){

            for(Amico amico : listaAmici){
                bw.write(amico.getNome()+","+amico.getCognome()+","+amico.getLavoro());
                bw.newLine();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Graaaaaandeeeee");
            alert.setHeaderText("Operazione riuscita");
            alert.setContentText("Dati salvati con successo");

            alert.showAndWait();

        }catch(IOException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Minghie...");
            alert.setHeaderText("Operazione fallita");
            alert.setContentText("I dati non sono stati salvati");

            e.printStackTrace();
        }

    }



}
