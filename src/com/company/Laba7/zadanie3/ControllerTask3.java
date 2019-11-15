package com.company.Laba7.zadanie3;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class ControllerTask3 {

    @FXML
    private ComboBox<String> ComboBoxFirst;

    ObservableList<String> list1 = FXCollections.observableArrayList(
            "Кузнечик",
                "Вася",
                "Крокодил",
                "Орел",
                "Трактор",
                "Боинг 747",
                "Слоник"
    );
    @FXML
    private ComboBox<String> ComboBoxSecond;

    ObservableList<String> list2 = FXCollections.observableArrayList(
            " ушел",
                    "полетел",
                    "побежал",
                    "залез",
                    "уполз",
                    "упрыгал",
                    "умчался"
    );

    @FXML
    private ComboBox<String> ComboBoxThird;

    ObservableList<String> list3 = FXCollections.observableArrayList(
            " в кусты",
                    "на дискотеку",
                    "домой",
                    "за пивом",
                    "на Чукотку",
                    "по грибы",
                    "на охоту"
    );

    @FXML
    private TextArea TextFieldMain;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonClose;

    @FXML
    void initialize() {

        ComboBoxFirst.setItems(list1);
        ComboBoxSecond.setItems(list2);
        ComboBoxThird.setItems(list3);

        ButtonClose.setOnAction(e -> Platform.exit());

        ButtonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String first = ComboBoxFirst.getValue();
                String second = ComboBoxSecond.getValue();
                String third = ComboBoxThird.getValue();
                String toAdd = first + " " + second + " " + third + "\n";
                TextFieldMain.appendText(toAdd);
            }
        });
    }

}

