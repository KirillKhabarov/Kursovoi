
package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowLine extends Shape {

    double arrowHeadSize;// размер кончика стрелки

    public ArrowLine(){


// определение пропорций кончика стрелки

        //arrowHeadSize=1.5*Math.sqrt((startX-endX)*(startX-endX)+(startX-endX)*(startX-endX));
        //  if (arrowHeadSize<2)arrowHeadSize=0.146*start.distance(end);
        //  if (arrowHeadSize<2)arrowHeadSize=0.236*start.distance(end);
        arrowHeadSize=12.5;
    }
    @Override
    public void draw(GraphicsContext gc) {


        drawLine(gc);//линия



    }

    public String toString() {

        return "линия";   }

    public void drawArrowstart(GraphicsContext gc) {
        gc.beginPath();
        gc.setFill(color);
        gc.setLineDashes();
        gc.strokeOval(startX-width, startY-width, 2*width, 2*width);   }



    public void drawLine(GraphicsContext gc) {
        gc.beginPath();
        gc.setFill(color);
        gc.setLineDashes();

        gc.setStroke(color);

        gc.setLineWidth(width);

        gc.moveTo(startX, startY);

        gc.lineTo(endX, endY);

        gc.stroke();// соединить точки
        gc.closePath();
    }



}

