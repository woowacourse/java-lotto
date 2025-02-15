import controller.Controller;
import model.lotto.LottoMachine;
import model.lotto.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class AppConfig {
    private Controller controller;
    private InputView inputView;
    private OutputView outputView;
    private LottoMachine lottoMachine;
    private RandomNumberGenerator randomNumberGenerator;

    public Controller controller() {
        if (this.controller == null) {
            this.controller = new Controller(inputView(), outputView(), lottoMaker());
        }
        return this.controller;
    }

    private InputView inputView() {
        if (this.inputView == null) {
            this.inputView = new InputView();
        }
        return this.inputView;
    }

    private OutputView outputView() {
        if (this.outputView == null) {
            this.outputView = new OutputView();
        }
        return this.outputView;
    }

    private LottoMachine lottoMaker() {
        if (this.lottoMachine == null) {
            this.lottoMachine = new LottoMachine(lottoNumberGenerator());
        }
        return this.lottoMachine;
    }

    private RandomNumberGenerator lottoNumberGenerator() {
        if (this.randomNumberGenerator == null) {
            this.randomNumberGenerator = new RandomNumberGenerator();
        }
        return this.randomNumberGenerator;
    }
}