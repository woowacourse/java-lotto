import controller.LottoController;
import domain.LottoFactory;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoFactory lottoFactory = new LottoFactory();

        LottoController lottoController = new LottoController(inputView, outputView, lottoFactory);
        lottoController.run();
    }
}
