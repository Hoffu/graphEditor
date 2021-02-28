package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.GraphModel;
import model.Point;

import javax.imageio.ImageIO;
import java.io.File;

public class Controller {
    public Canvas canvas;
    private GraphModel graphModel = new GraphModel();
    private boolean instrument = true;
    private Window primaryStage;

    public void mouseClicked(MouseEvent mouseEvent) {
        if (instrument) {
            graphModel.addPoint(new Point(mouseEvent.getSceneX(), mouseEvent.getSceneY(), Color.ROYALBLUE));
        } else {
            graphModel.removePoint((int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY());
        }
        repaintModel();
    }

    public void penClicked(ActionEvent actionEvent) {
        instrument = true;
    }

    public void easerClicked(ActionEvent actionEvent) {
        instrument = false;
    }

    public void repaintModel() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i = 0; i < graphModel.getPointCount(); i++) {
            canvas.getGraphicsContext2D().setFill(graphModel.getPoint(i).getColor());
            canvas.getGraphicsContext2D().fillOval(graphModel.getPoint(i).getX(), graphModel.getPoint(i).getY(), 6, 6);
        }
    }

    public Image loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Изображение", "*.png"));
        File loadedImage = fileChooser.showOpenDialog(primaryStage);
        return new Image(loadedImage.toURI().toString());
    }

    public void writeImage(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        graphModel.clearPoints();
        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {
                graphModel.addPoint(new Point(x, y, pixelReader.getColor(x, y)));
            }
        }
        repaintModel();
    }

    public void loadImageClicked(ActionEvent actionEvent) {
        writeImage(loadImage());
    }

    public void saveImageClicked(ActionEvent actionEvent) {
        WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите директорию...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Изображение", "*.png"));
        File file = fileChooser.showSaveDialog(primaryStage);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
