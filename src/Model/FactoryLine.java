package Model;

import javafx.scene.canvas.Canvas;

public class FactoryLine implements AbstractFactory {
    @Override
    public Shape CreateArrow(Canvas canvas) {
        ArrowLine arrow=new ArrowLine() ;
        return arrow;
    }
}