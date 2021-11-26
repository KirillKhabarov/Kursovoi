package sample;

import Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public ListView LV;
    public ColorPicker ColPic;
    public ListView LVleft;
    private Shape currentarrow;
    Image image;
    ArrayList<Shape> ls=new ArrayList<Shape>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<Shape> content= FXCollections.observableArrayList(new FactoryLine().CreateArrow(canvas),new FactoryAssotioon().CreateArrow(canvas),
                new FactoryDependence().CreateArrow(canvas), new FactoryGeneralization().CreateArrow(canvas));
        LV.setItems(content);
        ObservableList<Shape> contentleft= FXCollections.observableArrayList(new Signs("ICard.png","Карта"),new Signs("IKey.png","Ключ"),new Signs("IZamok.png","Замок"),new Signs("IFinish.png","Финиш"));
        LVleft.setItems(contentleft);
        LV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        LVleft.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        LV.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>() {
                                    @Override
                                    public ListCell<Shape> call(ListView<Shape> list) {
                                        ShapeCellHorizontal sh= new ShapeCellHorizontal();
                                        return sh;
                                    }
                                }
        );
        LVleft.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>() {
                              @Override
                              public ListCell<Shape> call(ListView<Shape> list) {
                                  ShapeCell sh= new ShapeCell();
                                  return sh;
                              }
                          }
        );
        currentarrow=content.get(0);
        LV.getSelectionModel().select(0);
        LV.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println(LV.getItems().toString());
                LVleft.getSelectionModel().select(-1);
                currentarrow=content.get(LV.getSelectionModel().getSelectedIndex());
            }
        });
        LVleft.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println(LVleft.getItems().toString());
                LV.getSelectionModel().select(-1);
                currentarrow=contentleft.get(LVleft.getSelectionModel().getSelectedIndex());
            }
        });
    }
//    public void onBegin(MouseEvent mouseEvent) {
//        currentarrow.setStartX(mouseEvent.getX());
//        currentarrow.setStartY(mouseEvent.getY());
//        currentarrow.draw(canvas.getGraphicsContext2D());
//
//    }

    public void onRun(MouseEvent mouseEvent) {
        currentarrow.setStartX(mouseEvent.getX());
        currentarrow.setStartY(mouseEvent.getY());
    }

    public void onEnd(MouseEvent mouseEvent) {
        currentarrow.setEndX(mouseEvent.getX());
        currentarrow.setEndY(mouseEvent.getY());
        System.out.println(currentarrow.toString());
        ls.add(currentarrow.clone());
        Paint();
       // ls.add(currentarrow.clone());
    }

    private void Paint(){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        for (Shape a:ls) {
            a.draw(canvas.getGraphicsContext2D());
        }
    }

    public void onBegin(MouseEvent mouseEvent) {
        Paint();
        currentarrow.setEndX(mouseEvent.getX());
        currentarrow.setEndY(mouseEvent.getY());
        currentarrow.draw(canvas.getGraphicsContext2D());
        currentarrow.setColor(ColPic.getValue());
//        canvas.getGraphicsContext2D().clearRect(1,1,1000,1000);
//        for (var a:ls) {
//            a.draw(canvas.getGraphicsContext2D());
//        }
//        currentarrow.draw(canvas.getGraphicsContext2D());
    }

    public void onMm(MouseEvent mouseEvent) {
        for (var a:ls) {
            if(((mouseEvent.getX()>a.getStartX() && mouseEvent.getX()<a.getEndX()) && (mouseEvent.getY()>a.getStartY() && mouseEvent.getY()<a.getEndY()))
             ||((mouseEvent.getX()<a.getStartX() && mouseEvent.getX()>a.getEndX()) && (mouseEvent.getY()<a.getStartY() && mouseEvent.getY()>a.getEndY()))
             ||((mouseEvent.getX()<a.getStartX() && mouseEvent.getX()>a.getEndX()) && (mouseEvent.getY()>a.getStartY() && mouseEvent.getY()<a.getEndY()))
             ||((mouseEvent.getX()>a.getStartX() && mouseEvent.getX()<a.getEndX()) && (mouseEvent.getY()<a.getStartY() && mouseEvent.getY()>a.getEndY())))
            {
                Tooltip.install(canvas,new Tooltip(a.toString()));
                return;
            }
        }
        Tooltip.install(canvas,null);
    }

    public void Open(ActionEvent actionEvent) {
        image=FileWorkSingleton.getInstance().OpenImg(canvas);
        Paint();
    }

    public void Save(ActionEvent actionEvent) {
        FileWorkSingleton.getInstance().exportImage(canvas);
    }

    public void Close(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void DelLast(ActionEvent actionEvent) {
        if (ls.size()<=0)return;
        ls.remove(ls.size()-1);
        Paint();
    }


}

