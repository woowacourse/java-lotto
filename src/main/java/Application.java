import controller.LottoController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        LottoController lottoController = new LottoController(outputView, inputView);
        lottoController.run();
    }
}
