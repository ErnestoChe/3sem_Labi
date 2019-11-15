package com.company.Laba7.zadanie1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField aKoefText;

    @FXML
    private TextField bKoefText;

    @FXML
    private TextField cKoefText;

    @FXML
    private Button buttonSolve;

    @FXML
    private Label KoefLabel;

    @FXML
    private Label DiscLabel;

    @FXML
    void initialize() {
        buttonSolve.setOnAction(
                new EventHandler<ActionEvent>() {
                    double a,b,c,D;
                    @Override public void handle(ActionEvent e) {
                        try{
                            a = Double.parseDouble(aKoefText.getText());
                            b = Double.parseDouble(bKoefText.getText());
                            c = Double.parseDouble(cKoefText.getText());

                            if(a == 0){
                                DiscLabel.setText("Линейное уравнение, D недоступен");
                            }else DiscLabel.setText("При D = " + Disc(a,b,c));
                            KoefLabel.setText(ans(a,b,c));

                        }catch (NumberFormatException e1){
                            DiscLabel.setText("неверные коэффициенты");
                        }
                    }
                }
        );
    }

    public double Disc(double a, double b, double c){
        double d = b * b - 4 * a * c;
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }

    public String ans(double a, double b, double c) {
        double D = Disc(a, b, c);
        D = Math.round(D * 100.0) / 100.0;
        String ans = "";
        if(a == 0){
            double x1 = -c / b;
            ans = "Линейное уравнение, корень х = " + x1;
        }
        else if (D > 0) {
            double x1 = (-b + Math.sqrt(D)) / (2 * a);
            double x2 = (-b - Math.sqrt(D)) / (2 * a);

            ans = "Корни: х1 = " + x1 + " и х2 = " + x2;
        } else if (D == 0) {
            double x1 = -b / (2 * a);
            ans = "Один корень х = " + x1;
        } else if (D < 0) {
            double im_x = Math.sqrt(-D) / (2 * a);
            double re_x = -b / (2 * a);

            im_x = Math.round(im_x * 10.0) / 10.0;
            re_x = Math.round(re_x * 10.0) / 10.0;

            ans = "Корни х1 = " + re_x + " + " + im_x + "*i  и х2 = " + re_x + " - " + im_x + "*i";
        }
        return ans;
    }

}
