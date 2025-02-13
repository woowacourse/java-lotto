package config;

import controller.Controller;
import service.LottoMaker;
import service.LottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoConfig {

    private Controller controller;
    private InputView inputView;
    private OutputView outputView;
    private LottoMaker lottoMaker;
    private LottoNumberGenerator lottoNumberGenerator;

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

    private LottoNumberGenerator lottoNumberGenerator() {
        if (this.lottoNumberGenerator == null) {
            this.lottoNumberGenerator = new LottoNumberGenerator();
        }
        return this.lottoNumberGenerator;
    }
}
