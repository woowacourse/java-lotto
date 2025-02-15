import controller.LottoController;
import controller.LottoDtoMapper;
import model.numbers.LottoNumberGenerator;
import model.rank.LottoRankCalculator;
import model.LottoStore;
import view.InputParser;
import view.InputView;
import view.LottoConsoleView;
import view.OutputView;

public class LottoConfig {

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
