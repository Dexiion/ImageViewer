package MVC.imageviewer.control;

import MVC.imageviewer.model.Image;
import MVC.imageviewer.view.ImageDisplay;
import java.util.List;

public class NextImageCommand implements Command {
    private final ImageDisplay imageDisplay;
    private final List<Image> images;

    public NextImageCommand(ImageDisplay imageDisplay, List<Image> images) {
        this.imageDisplay = imageDisplay;
        this.images = images;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(next());
    }

    private Image next() {
        return images.get(nextIndex());
    }

    private int nextIndex() {
        return (currentIndex() + 1) % images.size();
    }

    private int currentIndex() {
        return images.indexOf(imageDisplay.currentImage());
    }
}
