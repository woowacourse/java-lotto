package config;

import controller.LottoController;
import repository.LottoRepository;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoConfig {

    public static LottoController createController() {
        return new LottoController(InputView.create(), OutputView.create(), createService());
    }

    public static LottoService createService() {
        return new LottoService(LottoRepository.create());
    }

}
