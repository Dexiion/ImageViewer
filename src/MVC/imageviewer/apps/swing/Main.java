package MVC.imageviewer.apps.swing;

import MVC.imageviewer.control.Command;
import MVC.imageviewer.control.NextImageCommand;
import MVC.imageviewer.control.PrevImageCommand;
import MVC.imageviewer.model.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.CENTER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Main extends JFrame {
    private ImagePanel imageDisplay;
    private final Map<String, Command> commands = new HashMap<>();
    
    public static void main(String[] args)  {
        new Main().execute();
    }
    
    public Main() {
        super.setTitle("Image Viewer - Swing");
        super.setSize(800, 600);
        super.setLocation(300, 300);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        super.getContentPane().add(imagePanel());
        super.add(toolbar(), BorderLayout.SOUTH);
        
        List<Image> images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        
        this.commands.put("prev", new PrevImageCommand(imageDisplay, images));
        this.commands.put("next", new NextImageCommand(imageDisplay, images));
    }
    
    private void execute() {
        super.setVisible(true);
    }
    
    private JPanel imagePanel() {
        ImagePanel panel = new ImagePanel();
        this.imageDisplay = panel;
        
        return panel;
    }
    
    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(CENTER));
        
        toolbar.add(button("prev"));
        toolbar.add(button("next"));
        
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        
        return button;
    }

    

}
