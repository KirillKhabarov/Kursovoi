package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

public class ShapeCell extends ListCell<Shape> {// класс Product

    @Override

    public void updateItem(Shape item, boolean empty) {

        super.updateItem(item, empty);

        if (item != null) {

            Canvas cnv=new Canvas();

            cnv.setHeight(60);// задание размера элемента отображения

            cnv.setWidth(120);

            GraphicsContext gr=cnv.getGraphicsContext2D();

            Shape item1=(Shape)item.clone();//текущая фигура из списка ObservableList
            item1.setStartY(0);
            item1.setStartX(30);
            item1.setEndX(90);
            item1.setEndY(60);
            item1.draw(gr); // ее отрисовка на канве
            System.out.println(item1);
            setGraphic(cnv); //установка канвы вместо cell

        }

    }

}
