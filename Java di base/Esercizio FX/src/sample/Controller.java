package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.ToDoData;
import sample.datamodel.ToDoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    private List<ToDoItem> toDoItems;
    @FXML
    private ListView<ToDoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ContextMenu listContextMenu;
    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<ToDoItem> filteredList;

    private Predicate<ToDoItem> wantAllItems;
    private Predicate<ToDoItem> wantTodaysItems;

    public void initialize() {

        /**tutto questo malloppo è l'aggiunta manuale degli elementi alla lista, che viene a mancare di utilità
         * una volta che è presente il file */

        /*ToDoItem item1 = new ToDoItem("Mail birthday card", "Buy a 30th birthday card for John",
                LocalDate.of(2016, Month.APRIL, 25));

        ToDoItem item2 = new ToDoItem("Doctor's Appointment", "See Dr. Smith at 123 Main Street. Bring paperwork",
                LocalDate.of(2016, Month.MAY, 23));

        ToDoItem item3 = new ToDoItem("MFinish design proposal for client", "I promised Mike I0d email website mockups by friday 22nd April",
                LocalDate.of(2016, Month.APRIL, 22));

        ToDoItem item4 = new ToDoItem("Pickup Doug at the train station", "Doug's arriving on March 23 on the 5:00 train",
                LocalDate.of(2016, Month.MARCH, 23));

        ToDoItem item5 = new ToDoItem("Pick up dry cleaning", "The clothes should be ready by wednesday",
                LocalDate.of(2016, Month.APRIL, 20));

        toDoItems = new ArrayList<ToDoItem>();
        toDoItems.add(item1);
        toDoItems.add(item2);
        toDoItems.add(item3);
        toDoItems.add(item4);
        toDoItems.add(item5);

        ToDoData.getInstance().setTodoItems(toDoItems);*/

        //questo, insieme alla lambda expression, fa in modo di eliminare una voce

        listContextMenu = new ContextMenu(); //menu da tasto destro su voce
        MenuItem deleteMenuItem = new MenuItem("Delete" );
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        listContextMenu.getItems().addAll(deleteMenuItem); //aggiunge a tutti gli oggetti la possibilità di eliminazione

        /**la funzione seguente, insieme a todoListView.getSelectionModel().selectFirst() consente di
         visualizzare automaticamente la descrizione. ChangeListener ha a che fare con le lambda expressions, per ora
         prendilo per buono
         */
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem oldValue, ToDoItem newValue) {
                if (newValue != null) {
                    ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadLineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        //----------algoritmo per filtrare gli impegni

        wantAllItems = new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem toDoItem) {
                return true; //ritorna vero perchè restituisce tutto
            }
        };

        wantTodaysItems = new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem toDoItem) {
                return (toDoItem.getDeadline().equals(LocalDate.now()));
            }
        };

        filteredList = new FilteredList<ToDoItem>(ToDoData.getInstance().getTodoItems(), wantAllItems);


        //----------algoritmo per l'ordinamento secondo le date
        SortedList<ToDoItem> sortedList = new SortedList<ToDoItem>(filteredList,
                new Comparator<ToDoItem>() {
                    @Override
                    public int compare(ToDoItem o1, ToDoItem o2) {
                        return o1.getDeadline().compareTo(o2.getDeadline());
                    }
                });

        //prima impl. poco efficiente -> todoListView.getItems().setAll(ToDoData.getInstance().getTodoItems());
        //todoListView.setItems(ToDoData.getInstance().getTodoItems());
        /**con la seguente dichiarazione di setItems, gli impegni verranno messi in ordine di calendario*/
        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst(); //questo consente di avviare il primo in automatico


        /*La seguente fa in modo che un evento che abbia la data odierna (LocalDate.now) appaia in rosso,
        mentre un evento che abbia la data del giorno dopo appaia in marrone LocalDate.now().plusDays(1),
         in verde se sono giorni passati LocalDate.isBefore(LocalDate.now())*/
        todoListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
            @Override
            public ListCell<ToDoItem> call(ListView<ToDoItem> toDoItemListView) {
                ListCell<ToDoItem> cell = new ListCell<ToDoItem>() {
                    @Override
                    protected void updateItem(ToDoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getShortDescription());
                            if (item.getDeadline().equals(LocalDate.now())) {
                                setTextFill(Color.RED);
                            } else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.BROWN);
                            } else if (item.getDeadline().isBefore(LocalDate.now())) {
                                setTextFill(Color.GREEN);
                            }
                        }
                    }
                };

                //lamba expression... questo fa sì che facendo click destro sulla voce, appaia "Delete"

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }
                        });

                return cell;
            }
        });
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New ToDo Item"); //questa da il titolo alla finestra
        dialog.setHeaderText("Un'altra descrizione");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("toDoItemDialog.fxml"));
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("toDoItemDialog.fxml"));
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace(); //stampa dove si è verificato l'errore
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            //con la seguente diamo in pasto al risultato il nuovo oggetto
            ToDoItem newItem = controller.processResults();
            /**prima implementazione poco efficiente con ripopolazione della lista a ogni uso
             todoListView.getItems().setAll(ToDoData.getInstance().getTodoItems());
             questa qui sopra permette di mettere subito nella lista di sinistra il nuovo elemento */
            todoListView.getSelectionModel().select(newItem);
            //questa qui sopra fa si che il nuovo elemento, una volta registrato, venga selezionato (.select(newItem))
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        ToDoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE))
                deleteItem(selectedItem);
        }
    }

    public void deleteItem(ToDoItem item) {
        //finestra di allerta per confermare l'eliminazione
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete ToDo Item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ToDoData.getInstance().deleteToDoItem(item);
        }
    }

    @FXML
    //la seguente funzione di fatto è inutile perchè fa la stessa cosa della funzione operante sulle lambda
    public void handleClickListView() {
        ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadLineLabel.setText(item.getDeadline().toString());

        /*ALTERNATIVA
        StringBuilder sb = new StringBuilder(item.getDetails());
        //StringBuilder è una classe che permette di utilizzare stringhe modificabili
        sb.append("\n\n\n\n"); //append è un metodo di StringBuilder per concatenare stringhe
        sb.append("Due: ");
        sb.append(item.getDeadline().toString());
        itemDetailsTextArea.setText(sb.toString());

        ALTERNATIVA
        System.out.println("The selected item is " + item);
        questa sopra fa spuntare sull'output della console la shortDescription
        questo sotto fa spuntare i dettagli sull'app
        itemDetailsTextArea.setText(item.getDetails()); */
    }

    @FXML
    public void handleFilterButton() {
        ToDoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (filterToggleButton.isSelected()) {
            //stampa solo quelli odierni
            filteredList.setPredicate(wantTodaysItems);
            if(filteredList.isEmpty()){
                itemDetailsTextArea.clear();
                deadLineLabel.setText("");
                //cioè se la lista è vuota, allora non mostrare niente da nessuna parte
            } else if(filteredList.contains(selectedItem)) {
                todoListView.getSelectionModel().select(selectedItem);
            }else
                todoListView.getSelectionModel().selectFirst();
        } else {
            //stampiamo tutto
            filteredList.setPredicate(wantAllItems);
            todoListView.getSelectionModel().select(selectedItem);
        }
    }

    @FXML
    public void handleExit(){
        Platform.exit();
    }

}


