import controller.LottoController;
import model.LottoCustomerHistory;
import model.LottoNumberGenerator;
import model.LottoStore;
import view.InputView;
import view.LottoConsoleView;
import view.OutputView;

public class LottoConfig {


    public LottoController lottoController() {
        return new LottoController(lottoConsoleView(), lottoStore(), lottoCustomerHistory());
    }

    private LottoConsoleView lottoConsoleView() {
        return new LottoConsoleView(new InputView(), new OutputView());
    }

    private LottoCustomerHistory lottoCustomerHistory() {
        return LottoCustomerHistory.getInstance();
    }

    private LottoStore lottoStore() {
        return new LottoStore(new LottoNumberGenerator());
    }
}
