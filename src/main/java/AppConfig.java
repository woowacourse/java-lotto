import controller.Controller;
import model.lotto.LottoMachine;
import model.lotto.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class AppConfig {
    public Controller controller() {
        return new Controller(inputView(), outputView(), lottoMaker());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private LottoMachine lottoMaker() {
        return new LottoMachine(lottoNumberGenerator());
    }

    private RandomNumberGenerator lottoNumberGenerator() {
        return new RandomNumberGenerator();
    }
}