package Model;


import javafx.scene.canvas.Canvas;

public class FactoryGeneralization implements AbstractFactory {

    @Override
    public Shape CreateArrow(Canvas canvas) {
        ArrowGeneralization arrow=new ArrowGeneralization() ;
        return arrow;
    }
}