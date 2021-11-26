package Model;

import javafx.scene.canvas.Canvas;

public class FactoryDependence implements AbstractFactory {
    @Override
    public Shape CreateArrow(Canvas canvas) {
        ArrowDependence arrow=new ArrowDependence() ;
        return arrow;
    }
}
