package Model;

import javafx.scene.canvas.Canvas;

public interface AbstractFactory {
    Shape CreateArrow(Canvas canvas);
}
