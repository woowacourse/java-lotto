import controller.Controller;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Controller controller = new Controller(inputView, outputView);
        controller.run();

    }
}
