package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/*questa classe è quella che si definisce SINGLETON, cioè una classe con costruttore private che fa sì che possa
esistere un'unica istanza della stessa
 */

public class ToDoData {

    private static final ToDoData instance = new ToDoData();
    private static final String filename = "TodoListItems.txt";

    //Prima implementazione (inefficiente, ripopola ogni volta la lista): private List<ToDoItem> todoItems;
    //Observable è un'interfaccia che consente di tenere conto in maniera dinamica dei cambiamenti di una Collection
    //in questo modo riusciamo ad aggiornare automaticamente la lista
    private ObservableList<ToDoItem> todoItems;
    private final DateTimeFormatter formatter;

    public static ToDoData getInstance(){
        return instance;
    }


    /*public void setTodoItems(List<ToDoItem> todoItems) {
        this.todoItems = todoItems;
    }*/

    public ObservableList<ToDoItem> getTodoItems() {
        return todoItems;
    }

    private ToDoData(){
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void loadToDoItems() throws IOException {

        todoItems = FXCollections.observableArrayList();
        /*usiamo observableArrayList perchè la funzione presente in Controller, "setAll()" agisce sulla classe
        Observable List
         */
        Path path = Paths.get(filename); //prende il percorso del file
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while ((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                ToDoItem toDoItem = new ToDoItem(shortDescription, details, date);
                todoItems.add(toDoItem);
                //N.B. questa funzione di aggiunta alla lista, se tolta, resetta il file a ogni riavvio!!
            }
        } finally{
            br.close();
        }
    }

    public void addTodoItem(ToDoItem item){
        todoItems.add(item);
    }

    public void storeTodoItems() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<ToDoItem> iter = todoItems.iterator();
            while(iter.hasNext()){
                ToDoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s", item.getShortDescription(), item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally{
            if(bw != null){
                bw.close();
            }
        }
    }

    public void deleteToDoItem(ToDoItem item){
        todoItems.remove(item);
    }

}
