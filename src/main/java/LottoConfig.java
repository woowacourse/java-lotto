import controller.LottoController;
import controller.dto.LottoDtoMapper;
import model.LottoNumbersGenerator;
import model.LottoRankCalculator;
import model.LottoStore;
import view.InputView;
import view.LottoConsoleView;
import view.LottoParser;
import view.OutputView;

public final class LottoConfig {

    private static final LottoConfig INSTANCE = new LottoConfig();

    private LottoConfig() {
    }

    public static LottoConfig getInstance() {
        return INSTANCE;
    }

    public LottoController lottoController() {
        return new LottoController(lottoConsoleView(), lottoStore());
    }

    private LottoConsoleView lottoConsoleView() {
        return new LottoConsoleView(new InputView(), new OutputView(), new LottoParser());
    }

    private LottoStore lottoStore() {
        return new LottoStore(new LottoNumbersGenerator(), new LottoRankCalculator(),  new LottoDtoMapper());
    }
}
