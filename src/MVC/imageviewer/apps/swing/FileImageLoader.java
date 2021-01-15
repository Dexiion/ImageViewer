package MVC.imageviewer.apps.swing;

import MVC.imageviewer.model.Image;
import MVC.imageviewer.view.ImageLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileImageLoader implements ImageLoader {
    private final File folder;

    public FileImageLoader(File folder) {
        this.folder = folder;
    }
    
    @Override
    public List<Image> load() {
        List<Image> list = new ArrayList<>();
        File[] files = folder.listFiles(withExtension(".jpg", ".png"));
        
        for (File file : files) {
            list.add(new Image(file.getAbsolutePath()));
        }
        
        return list;
    }

    private FilenameFilter withExtension(String ... extensions) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for (String extension : extensions) {
                    if (name.endsWith(extension)) return true;
                }
                
                return false;
            }
        };
    }

}
