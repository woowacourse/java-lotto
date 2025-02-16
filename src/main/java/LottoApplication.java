import controller.LottoController;
import model.LottoNumberGenerator;
import model.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RandomNumberGenerator randomNumberGenerator = new LottoNumberGenerator();

        new LottoController(inputView, outputView, randomNumberGenerator).run();
    }
}
