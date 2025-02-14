package config;

import controller.LottoController;

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
    return new LottoService();
  }
}

