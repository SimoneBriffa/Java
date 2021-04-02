package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.ToDoData;
import sample.datamodel.ToDoItem;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadLinePicker;

    public ToDoItem processResults(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadLineValue = deadLinePicker.getValue();
        ToDoItem newItem = new ToDoItem(shortDescription, details, deadLineValue);
        ToDoData.getInstance().addTodoItem(newItem);

        return newItem;

        //facciamo in modo che la funzione ritorni l'oggetto così che venga selezionato una volta registrato
    }
}
