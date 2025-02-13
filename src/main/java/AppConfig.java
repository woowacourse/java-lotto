import controller.Controller;
import model.lotto.RandomNumberGenerator;
import service.LottoMaker;
import view.InputView;
import view.OutputView;

public class AppConfig {
    private Controller controller;
    private InputView inputView;
    private OutputView outputView;
    private LottoMaker lottoMaker;
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

    private LottoMaker lottoMaker() {
        if (this.lottoMaker == null) {
            this.lottoMaker = new LottoMaker(lottoNumberGenerator());
        }
        return this.lottoMaker;
    }

    private RandomNumberGenerator lottoNumberGenerator() {
        if (this.randomNumberGenerator == null) {
            this.randomNumberGenerator = new RandomNumberGenerator();
        }
        return this.randomNumberGenerator;
    }
}