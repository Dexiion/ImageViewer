package MVP.imageviewer.apps.swing;

import MVP.imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {
    private BufferedImage bitmap;
    private BufferedImage futureBitmap;
    private String current;
    private String future;
    private int offset = 0;
    private Shift shift;

    public ImagePanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(bitmap == null) return;
        g.drawImage(bitmap, offset, 0, null);
        
        if (offset == 0) return;
        g.drawImage(futureBitmap, offset < 0 ? bitmap.getWidth()+offset : -(futureBitmap.getWidth()-offset), 0, null);
    }

    @Override
    public void display(String image) {
        this.current = image;
        this.bitmap = loadBitmap(image);
        repaint();
    }
    
    private void setOffset(int offset) {
            this.offset = offset;
            if (offset > 0) setFuture(shift.right());
            if (offset < 0) setFuture(shift.left());
            
            repaint();
    }
    
    private void toggle() {
        this.current = this.future;
        this.bitmap = this.futureBitmap;
        setOffset(0);
    }

    private void setFuture(String name) {
        if (name.equals(future)) return;
        this.future = name;
        this.futureBitmap = loadBitmap(name);
    }
    
    @Override
    public String current() {
        return current;
    }

    private BufferedImage loadBitmap(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private class MouseHandler implements MouseListener,MouseMotionListener{

        private int initial;

        public MouseHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            initial = e.getX();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (Math.abs(offset) > getWidth() / 2) 
                toggle();
            else
                setOffset(0);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            setOffset(e.getX()-initial);
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }

    }
    
}
