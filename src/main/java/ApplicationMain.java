import controller.Controller;
import service.LottoMaker;
import view.InputView;
import view.OutputView;

public class ApplicationMain {
    public static void main(String[] args) {
        Controller controller = new Controller(new InputView(), new OutputView(), new LottoMaker());
        controller.start();
    }
}