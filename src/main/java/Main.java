import jade.Window;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting");
        Window window = Window.get();
        System.out.println("Window created");
        window.run();
    }
}
