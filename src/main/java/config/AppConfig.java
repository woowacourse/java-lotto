package config;

import controller.LottoController;
import generator.DefaultLottoNumber;
import generator.RandomGenerator;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public LottoController createLottoController() {
        return new LottoController(createInputView(), createOutputView(), createLottoService());
    }

    public InputView createInputView() {
        return new InputView();
    }

    public OutputView createOutputView() {
        return new OutputView();
    }

    public LottoService createLottoService() {
        return new LottoService(createRandomGenerator());
    }

    public RandomGenerator createRandomGenerator() {
        return new DefaultLottoNumber();
    }
}

