import controller.MainController;

public class Application {
    public static void main(String[] args) {
        final MainController mainController = MainController.create();
        mainController.run();
    }
}