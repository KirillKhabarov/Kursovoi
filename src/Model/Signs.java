package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Signs extends Shape{
    Image image;
    String discription;
    public Signs(String s,String name) {
        super();
        image = new Image("Img/"+s);
        discription=name;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,startX,startY,endX-startX,endY-startY);
    }

    @Override
    public String toString() {
        return discription;
    }
}
