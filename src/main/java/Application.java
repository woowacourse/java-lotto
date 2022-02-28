import controller.LottoController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        final LottoController lottoController = new LottoController(new InputView(), new OutputView());
        lottoController.start();
    }
}
