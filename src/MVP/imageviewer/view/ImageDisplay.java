package MVP.imageviewer.view;


public interface ImageDisplay {
    
    void display(String name);
    public String current();
    void on(Shift shift);
    
    interface Shift {
        String left();
        String right();
    }

}
