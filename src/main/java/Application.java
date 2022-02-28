import controller.Controller;
import controller.ManualLottoController;

public class Application {
    public static void main(String[] args) {
        Controller controller = new ManualLottoController();
        controller.run();
    }
}
