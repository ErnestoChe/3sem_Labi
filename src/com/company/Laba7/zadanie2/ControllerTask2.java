package com.company.Laba7.zadanie2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ControllerTask2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextXCoord;

    @FXML
    private TextField TextYCoord;

    @FXML
    private Button ButtonCheck;

    @FXML
    private Label LabelResult;

    @FXML
    void initialize() {
        ButtonCheck.setOnAction(
                new EventHandler<ActionEvent>() {
                    double x, y;
                    @Override public void handle(ActionEvent e) {
                        try{
                            x = Double.parseDouble(TextXCoord.getText());
                            y = Double.parseDouble(TextYCoord.getText());

                            if(isHit(x, y)){
                                LabelResult.setText("Точка (" + x + "; " + y + ") лежит в этой области");
                                LabelResult.setTextFill(Color.BLUE);
                            }else{
                                LabelResult.setText("Точка (" + x + "; "+ y + ") не попала в эту область");
                                LabelResult.setTextFill(Color.RED);
                            }
                        }catch(NumberFormatException ex){
                            LabelResult.setText("Введены некорректные значения");
                            LabelResult.setTextFill(Color.RED);
                        }

                    }
                }
        );
    }

    public boolean isHit(double x, double y){
        if(x >= 0 && x <= 3){
            if(x * x + y * y <= 36){
                return true;
            } else return false;
        }else if(x >= -3 && x < 0){
            if(y >= 0 && y <= 6){
                if(y <= 2 * x + 6){
                    return true;
                }else return false;
            }else return false;
        }else return false;
    }
}

