package MVC.imageviewer.apps.mock;

import MVC.imageviewer.control.Command;
import MVC.imageviewer.control.InitCommand;
import MVC.imageviewer.model.Image;
import MVC.imageviewer.view.ImageDisplay;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static Command NullCommand = new Command.Null();
    private final Map<String, Command> commands = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        new Main().execute();
    }
    
    public Main() {
        List<Image> images = new MockImageLoader().load();
        ImageDisplay imageDisplay = new MockImageDisplay();
        
        InitCommand init = new InitCommand(imageDisplay, images);
        init.execute();
        commands.putAll(init.getCommands());
    }

    private String input() {
        return scanner.next().toUpperCase();
    }

    private void execute() {
        while (true) commands.getOrDefault(input(), NullCommand).execute();
    }
}
