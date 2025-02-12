import controller.LottoController;
import controller.dto.LottoDtoMapper;
import model.LottoCustomerHistory;
import model.LottoNumberGenerator;
import model.LottoStore;
import view.InputParser;
import view.InputView;
import view.LottoConsoleView;
import view.OutputView;

public class LottoConfig {


    public LottoController lottoController() {
        return new LottoController(lottoConsoleView(), lottoStore(), lottoCustomerHistory(), new LottoDtoMapper());
    }

    private LottoConsoleView lottoConsoleView() {
        return new LottoConsoleView(new InputView(), new OutputView(), new InputParser());
    }

    private LottoCustomerHistory lottoCustomerHistory() {
        return LottoCustomerHistory.getInstance();
    }

    private LottoStore lottoStore() {
        return new LottoStore(new LottoNumberGenerator());
    }
}
