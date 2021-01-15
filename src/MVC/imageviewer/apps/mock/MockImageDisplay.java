package MVC.imageviewer.apps.mock;

import MVC.imageviewer.model.Image;
import MVC.imageviewer.view.ImageDisplay;

public class MockImageDisplay implements ImageDisplay {
    private Image image;

    @Override
    public void display(Image image) {
        this.image = image;
        System.out.println(image.getName());
    }

    @Override
    public Image currentImage() {
        return image;
    }
}
