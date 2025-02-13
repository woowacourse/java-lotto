import controller.Controller;
import view.InputView;

public class ApplicationMain {
    public static void main(String[] args) {
        Controller controller = new Controller(new InputView());
        controller.start();
    }
}