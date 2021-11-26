
package Model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public final class FileWorkSingleton {
    private static FileWorkSingleton _instance = null;
    private FileWorkSingleton() {}
    public static synchronized FileWorkSingleton getInstance() {
        if (_instance == null)
            _instance = new FileWorkSingleton();
        return _instance;
    }

    public void exportImage(Canvas can) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл...");
        fileChooser.setInitialFileName("Компоненты");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.png"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileOutputStream out = new FileOutputStream(file)) {
                WritableImage wi = new WritableImage((int)can.getWidth(),(int)can.getHeight());
                ImageIO.write(SwingFXUtils.fromFXImage(can.snapshot(null,wi),null),"png",out);
                out.close();
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Сохранено");
                alert.setTitle("Результат сохранения");
                alert.show();
            } catch (IOException ex) {
                System.out.println(ex);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Не сохранено");
                alert.setTitle("Результат сохранения");
                alert.show();
            }

        }
    }

    public Image OpenImg(Canvas can){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открытие файла");
        fileChooser.setInitialFileName("new_image");
        fileChooser.getExtensionFilters().add(new
                FileChooser.ExtensionFilter("Изображение", "*.png"));
        File loadImageFile=fileChooser.showOpenDialog(can.getScene().getWindow());
        Image image = new Image(loadImageFile.toURI().toString());
        return image;
    }
}


