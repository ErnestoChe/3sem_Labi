package com.company.Laba7.zadanie4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerTask4 {

    @FXML
    private RadioButton RadioEasy;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton RadioMedium;

    @FXML
    private RadioButton RadioHard;

    @FXML
    private Button ButtonStartStop;

    @FXML
    private Label LabelTaunt;

    @FXML
    private Button ButtonToCatch;

    private Timer timer;
    private boolean started;

    @FXML
    void initialize() {
        started = false;

        ButtonStartStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(started){
                    timer.cancel();
                    started = false;
                    LabelTaunt.setText("Не поймал");
                    ButtonStartStop.setText("Начать снова");
                }else {
                    started = true;
                    LabelTaunt.setText("Лови");
                    ButtonStartStop.setText("Стоп, я сдаюсь");
                    if(RadioEasy.isSelected()){
                        randomSpawn(1000);
                    }else if(RadioMedium.isSelected()){
                        randomSpawn(500);
                    }else if(RadioHard.isSelected()){
                        randomSpawn(250);
                    }
                }
            }
        }
        );
        ButtonToCatch.setOnAction(e -> {
            LabelTaunt.setText("Пиймав на гендзюцу");
            timer.cancel();
            started = false;
        });
    }

    public void randomSpawn(int a){
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Random r = new Random();
                        int y = r.nextInt(250);
                        int x = r.nextInt(300) - 300;
                        ButtonToCatch.setTranslateY(y);
                        ButtonToCatch.setTranslateX(x);
                    }
                }, 0, a);
    }
}



// x -300 0
// y  0 250