package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import sample.model.Album;
import sample.model.Artist;
import sample.model.Datasource;

import java.util.zip.DataFormatException;

public class Controller {

    @FXML
    private TableView artistTable;

    @FXML
    private ProgressBar progressBar;

    /*The TableView control is designed to visualize an unlimited number of rows of data, broken out
    into columns. A TableView is therefore very similar to the ListView control, with the addition of support
    for columns. For an example on how to create a TableView, refer to the 'Creating a TableView' control
    section below*/

    @FXML
    public void listArtists(){
        Task<ObservableList<Artist>> task = new getAllArtistTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        //lamba expression
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void updateArtist(){
        /*normalmente faremmo:
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
         */
        final Artist artist = (Artist) artistTable.getItems().get(2); //get(2) = secondo elemento della lista(gli acdc)

        Task<Boolean> task = new Task<Boolean>(){
            @Override
            protected Boolean call() throws Exception{
                return Datasource.getInstance().updateArtistName(artist.getId(), "AC/DC");
            }
        };

        task.setOnSucceeded(e -> {
            if(task.valueProperty().get()){
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });

        new Thread(task).start();

    }


    @FXML
    public void listAlbumsForArtist(){
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null){
            System.out.println("No artist selected");
            return;
        }
        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>(){
            @Override
            public ObservableList<Album> call(){
                return FXCollections.observableArrayList(Datasource.getInstance().queryAlbumForArtistId(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

}

class getAllArtistTask extends Task {

    /*ogni classe che erediti Task deve sovrascrivere il metodo call()*/

    @Override
    public ObservableList<Artist> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().queryArtist(Datasource.ORDER_BY_ASC));
    }
}