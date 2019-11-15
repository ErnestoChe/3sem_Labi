package com.company.Laba8;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ControllerTask1 {

    @FXML
    private Tab tabLine;

    @FXML
    private Canvas lineCanvas;

    @FXML
    private TextField lineStartX;

    @FXML
    private TextField lineStartY;

    @FXML
    private TextField lineEndX;

    @FXML
    private TextField lineEndY;

    @FXML
    private Button drawLine;

    @FXML
    private Tab tabRectangle;

    @FXML
    private Canvas rectCanvas;

    @FXML
    private TextField rectFirstAngleX;

    @FXML
    private TextField rectFirstAngleY;

    @FXML
    private TextField rectSecondAngleX;

    @FXML
    private TextField rectSecondAngleY;

    @FXML
    private Button drawRect;

    @FXML
    private RadioButton fillRect;

    @FXML
    private Tab tabCircle;

    @FXML
    private Canvas circleCanvas;

    @FXML
    private TextField circleFirstAngleX;

    @FXML
    private TextField circleFirstAngleY;

    @FXML
    private TextField circleSecondAngleX;

    @FXML
    private TextField circleSecondAngleY;

    @FXML
    private Button drawCircle;

    @FXML
    private RadioButton fillCircle;

    @FXML
    private Tab tabTriangle;

    @FXML
    private Canvas triangleCanvas;

    @FXML
    private TextField firstX;

    @FXML
    private TextField firstY;

    @FXML
    private TextField secondX;

    @FXML
    private TextField secondY;

    @FXML
    private Button drawTriangle;

    @FXML
    private RadioButton fillTriangle;

    @FXML
    private TextField thirdX;

    @FXML
    private TextField thirdY;

    @FXML
    private Button drawSquareFr;

    @FXML
    private Canvas fractalCanvas;

    @FXML
    private Button drawFractalTree;

    @FXML
    private Canvas fractalTreeCanvas;

    @FXML
    void initialize() {
        //линия
        drawLine.setOnAction(new EventHandler<ActionEvent>() {
            GraphicsContext gcLine = lineCanvas.getGraphicsContext2D();
            @Override public void handle(ActionEvent e) {
                double x1, y1, x2, y2;
                try{
                    x1 = Double.parseDouble(lineStartX.getText());
                    y1 = Double.parseDouble(lineStartY.getText());
                    x2 = Double.parseDouble(lineEndX.getText());
                    y2 = Double.parseDouble(lineEndY.getText());
                    gcLine.strokeLine(x1, y1, x2, y2);
                }catch (NumberFormatException exception){
                    System.out.println("данные не те");
                }
            }
        });
        //прямоугольник
        drawRect.setOnAction(new EventHandler<ActionEvent>() {
            GraphicsContext gcRectangle = rectCanvas.getGraphicsContext2D();
            @Override
            public void handle(ActionEvent event) {
                double x1, y1, x2, y2;
                try{
                    x1 = Double.parseDouble(rectFirstAngleX.getText());
                    y1 = Double.parseDouble(rectFirstAngleY.getText());
                    x2 = Double.parseDouble(rectSecondAngleX.getText());
                    y2 = Double.parseDouble(rectSecondAngleY.getText());
                    if(fillRect.isSelected()){
                        gcRectangle.fillRect(x1, y1, x2-x1, y2-y1);
                    }else gcRectangle.strokeRect(x1, y1, x2-x1, y2-y1);
                }catch (NumberFormatException exception){
                    System.out.println("данные не те");
                }
            }
        });
        //эллипсы
        drawCircle.setOnAction(new EventHandler<ActionEvent>() {
            GraphicsContext gcCircle = circleCanvas.getGraphicsContext2D();
            @Override
            public void handle(ActionEvent event) {
                double x1, y1, x2, y2;
                try{
                    x1 = Double.parseDouble(circleFirstAngleX.getText());
                    y1 = Double.parseDouble(circleFirstAngleY.getText());
                    x2 = Double.parseDouble(circleSecondAngleX.getText());
                    y2 = Double.parseDouble(circleSecondAngleY.getText());
                    if(fillCircle.isSelected()){
                        gcCircle.fillOval(x1, y1, x2-x1, y2-y1);
                    }else gcCircle.strokeOval(x1, y1, x2-x1, y2-y1);
                }catch (NumberFormatException exception){
                    System.out.println("данные не те");
                }
            }
        });
        //треугольник
        drawTriangle.setOnAction(new EventHandler<ActionEvent>() {
            GraphicsContext gcTriangle = triangleCanvas.getGraphicsContext2D();
            @Override
            public void handle(ActionEvent event) {
                double[] x = new double[3], y = new double[3];
                try{
                    x[0] = Double.parseDouble(firstX.getText());
                    y[0] = Double.parseDouble(firstY.getText());
                    x[1] = Double.parseDouble(secondX.getText());
                    y[1] = Double.parseDouble(secondY.getText());
                    x[2] = Double.parseDouble(thirdX.getText());
                    y[2] = Double.parseDouble(thirdY.getText());

                    if(fillTriangle.isSelected()){
                        gcTriangle.fillPolygon(x, y, 3);
                    }else gcTriangle.strokePolygon(x, y, 3);
                }catch(NumberFormatException exception){
                    System.out.println("dannie ne te");
                }
            }
        });
        //квадратный фрактал
        drawSquareFr.setOnAction(new EventHandler<ActionEvent>() {
            GraphicsContext gcFractal = fractalCanvas.getGraphicsContext2D();
            @Override
            public void handle(ActionEvent event) {
                double x0 = fractalCanvas.getWidth()/2;
                double y0 = fractalCanvas.getHeight()/2;
                System.out.println(fractalCanvas.getWidth());
                System.out.println(fractalCanvas.getHeight());
                System.out.println(x0);
                System.out.println(y0);
                int w = 100;
                drawSquare(gcFractal, x0, y0, w);
                recursiveDraw(gcFractal, x0, y0, w, 0);
            }
        });
        //фрактальное дерево
        drawFractalTree.setOnAction(new EventHandler<ActionEvent>() {
            GraphicsContext gcTree = fractalTreeCanvas.getGraphicsContext2D();
            @Override
            public void handle(ActionEvent event) {
                double startX = 0;
                double startY = fractalCanvas.getHeight()/2;
                double angle = 0;
                double change = 30;
                drawTree(gcTree, startX, startY, angle, 5, change);
            }
        });

    }

    public void drawTree(GraphicsContext gc, double x1, double y1, double angle, double depth, double change){
        if(depth == 0) return;
        double x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 12.0);
        double y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 12.0);

        gc.strokeLine(x1,y1,x2,y2);
        drawTree(gc, x2, y2, angle - change, depth-1, change);
        drawTree(gc, x2, y2, angle + change, depth-1, change);

    }

    public void drawSquare(GraphicsContext gc, double midX, double midY,  int width){
        gc.strokeRect(midX - width/2, midY - width/2, width, width);
    }

    public void recursiveDraw(GraphicsContext gc, double x, double y, int w, int state) {
        drawSquare(gc, x, y, w);
        if (w > 4){

            //right
            if(state != 3)
            recursiveDraw(gc, x + 3 * w / 4, y, w / 2, 1);
            //up
            if(state != 4)
            recursiveDraw(gc, x, y - 3 * w / 4, w / 2, 2);
            //left
            if(state != 1)
            recursiveDraw(gc, x - 3 * w / 4, y, w / 2, 3);
            //down
            if(state != 2)
            recursiveDraw(gc, x, y + 3 * w / 4, w / 2, 4);
        }
    }
}

