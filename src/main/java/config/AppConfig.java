package config;

import controller.LottoController;

import view.InputView;
import view.OutputView;

public class AppConfig {

  public LottoController createLottoController() {
    return new LottoController(createInputView(), createOutputView());
  }

  public InputView createInputView() {
    return new InputView();
  }

  public OutputView createOutputView() {
    return new OutputView();
  }
}

