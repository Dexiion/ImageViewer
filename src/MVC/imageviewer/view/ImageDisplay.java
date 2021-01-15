package MVC.imageviewer.view;

import MVC.imageviewer.model.Image;

public interface ImageDisplay {
    
    void display(Image image);
    
    public Image currentImage();

}
