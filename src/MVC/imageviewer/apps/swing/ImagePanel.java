package MVC.imageviewer.apps.swing;

import MVC.imageviewer.model.Image;
import MVC.imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {
    private BufferedImage bitmap;
    private Image image;
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        Scale s = new Scale(bitmap.getWidth(), bitmap.getHeight(), getWidth(), getHeight());
        g.drawImage(bitmap, s.x(), s.y(), s.width(), s.height(), null);
    }

    @Override
    public void display(Image image) {
        this.image = image;
        loadBitmap();
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }

    private void loadBitmap() {
        try {
            bitmap = ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            bitmap = null;
        }
    }
    
    // iw: Image width
    // ig: Image height
    // pw: Panel width
    // ph : Panel height
    // ir: Image Ratio = Image Width / Image Height
    // pr: Panel Ratio = Panel Width / Panel Height
    
    // 800 x 600 (1.3333) -> 400 x 4300 (1.3333) -> 400 x 300
    // 800 x 600 (1.3333) -> 1600 x 1200 (1.3333) -> 1600 x 1200
    // 800 x 600 (1.3333) -> 400 x 600 (0.6666) -> 400 x 60 * 400 / 800
    // 800 x 600 (1.3333) -> 800 x 300 (2.6666) -> 800 * 300 / 600 x 300
    
    // ir > pr => pw x ih * pw / iw
    // ir < pr -> iw * ph / ih x ph
    
    // image (800 x 600) -> panel (400, 55) -> scale(800 * (55 / 600) x 55)

    private static class Scale {
        private final int iw, ih, pw, ph;
            
        private Scale(int iw, int ih, int pw, int ph) {
            this.iw = iw;
            this.ih = ih;
            this.pw = pw;
            this.ph = ph;
        }
        
        private int x() {
            return (pw - width()) / 2;
        }
        
        private int y() {
            return (ph - height()) / 2;
        }
        
        private int width() {
            return adjustWidth() ? pw : (int) (ih * (double) pw / iw);
        }
        
        private int height() {
            return adjustHeight() ? (int) (iw * (double) ph / ih) : ph;
        }
        
        private boolean adjustWidth() {
            return iw * ph > pw * ih;
        }

        private boolean adjustHeight() {
            return ih * pw > ph * iw;
        }
    }
    
}
