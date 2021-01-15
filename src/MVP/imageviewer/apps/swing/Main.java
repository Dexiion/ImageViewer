package MVP.imageviewer.apps.swing;

import MVP.imageviewer.control.ImagePresenter;
import MVP.imageviewer.model.Image;
import MVP.imageviewer.view.ImageDisplay;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    private ImageDisplay imageDisplay;
    public static int MAX_SIZE = 100_000;
    
    public static void main(String[] args)  {
        new Main().execute();
    }
    private ImagePresenter imagePresenter;
    
    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        
        this.imagePresenter = createImagePresenter();
    }
    
    private void execute() {
        this.setVisible(true);
    }
    
    private JPanel imagePanel() {
        ImagePanel panel = new ImagePanel();
        this.imageDisplay = panel;
        
        return panel;
    }

    private ImagePresenter createImagePresenter() {
        return new ImagePresenter(loadImages(), imageDisplay);
    }

    private List<Image> loadImages() {
        return new FileImageLoader(new File("fotos")).load();
    }

}
