package MVC.imageviewer.control;

import MVC.imageviewer.model.Image;
import MVC.imageviewer.view.ImageDisplay;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InitCommand implements Command {
    private final Map<String, Command> commands = new HashMap<>();
    private final ImageDisplay imageDisplay;
    private final List<Image> images;

    public InitCommand(ImageDisplay imageDisplay, List<Image> images) {
        this.imageDisplay = imageDisplay;
        this.images = images;
    }
    
    @Override
    public void execute() {        
        commands.put("N", new NextImageCommand(imageDisplay, images));
        commands.put("P", new PrevImageCommand(imageDisplay, images));
        commands.put("Q", new ExitImageCommand());
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
