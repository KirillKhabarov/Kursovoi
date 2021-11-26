package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class FactoryAssotioon implements AbstractFactory {
    @Override
    public Shape CreateArrow(Canvas canvas) {
        ArrowAssotioon arrow=new ArrowAssotioon() ;
        return arrow;
    }
}