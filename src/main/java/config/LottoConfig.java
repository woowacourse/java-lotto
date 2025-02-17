package config;

import controller.LottoController;
import repository.LottoRepository;
import repository.LottoResultRepository;
import view.InputView;
import view.OutputView;

public class LottoConfig {

    public static LottoController createController() {
        return new LottoController(InputView.create(), OutputView.create(),
            LottoRepository.create(), LottoResultRepository.create());
    }

}
