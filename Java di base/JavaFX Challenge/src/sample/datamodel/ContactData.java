package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactData {

    private ObservableList<Contact> contacts;

    public ContactData() {
        contacts = FXCollections.observableArrayList();
    }

    public ObservableList<Contact> getContacts(){
        return contacts;
    }

    public void addContact(Contact item){
        contacts.add(item);
    }

    public void deleteContact(Contact item){
        contacts.remove(item);
    }

}
