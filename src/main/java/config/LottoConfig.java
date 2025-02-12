package config;

import controller.LottoController;
import view.InputView;
import view.OutputView;

public class LottoConfig {

    public static LottoController create(){
        return new LottoController(InputView.create(), OutputView.create());
    }

}
