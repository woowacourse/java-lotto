package infrastructure;

import controller.LottoController;
import service.LottoManager;
import service.RandomLottoNumbersGenerator;
import service.RandomNumbersGenerator;
import view.InputView;
import view.OutputView;

public class AppConfig {
    public LottoController controller() {
        return new LottoController(new InputView(), new OutputView(), lottoManager());
    }

    private LottoManager lottoManager() {
        return new LottoManager(randomNumbersGenerator());
    }

    private RandomNumbersGenerator randomNumbersGenerator() {
        return new RandomLottoNumbersGenerator();
    }
}
