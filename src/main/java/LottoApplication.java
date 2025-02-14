import controller.LottoController;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        LottoController lottoController = new LottoController(inputView, outputView);

        lottoController.run();
    }
}
