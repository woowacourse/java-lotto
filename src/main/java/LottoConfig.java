import controller.LottoController;
import controller.dto.LottoDtoMapper;
import model.LottoNumberGenerator;
import model.LottoRankCalculator;
import model.LottoStore;
import view.InputParser;
import view.InputView;
import view.LottoConsoleView;
import view.OutputView;

public final class LottoConfig {

    private static final LottoConfig INSTANCE = new LottoConfig();

    private LottoConfig() {
    }

    public static LottoConfig getInstance() {
        return INSTANCE;
    }

    public LottoController lottoController() {
        return new LottoController(lottoConsoleView(), lottoStore(), new LottoDtoMapper());
    }

    private LottoConsoleView lottoConsoleView() {
        return new LottoConsoleView(new InputView(), new OutputView(), new InputParser());
    }

    private LottoStore lottoStore() {
        return new LottoStore(new LottoNumberGenerator(), new LottoRankCalculator());
    }
}
