package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Album {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty artistId;

    public Album() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.artistId = new SimpleIntegerProperty();
    }

    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getArtistId() {
        return this.artistId.get();
    }

    public void setArtistId(int artistId) {
        this.artistId.set(artistId);
    }
}
