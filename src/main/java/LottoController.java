import domain.Lotto;
import domain.LottoShop;
import domain.LottoWallet;
import domain.Money;
import domain.WinningLotto;
import domain.WinningResult;
import java.io.IOException;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoShop lottoShop;

    public LottoController(InputView inputView, OutputView outputView, LottoShop lottoShop) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoShop = lottoShop;
    }

    public void run() throws IOException {
        Money money = inputView.inputMoney();

        LottoWallet lottoWallet = lottoShop.buyLottos(money);
        outputView.printLottos(lottoWallet);

        WinningLotto winningLotto = inputView.inputWinningLotto();

        WinningResult winningResult = winningLotto.calculateWinningResult(lottoWallet);
        outputView.printWinningResult(winningResult);
        outputView.printRevenue(winningResult.calculateRevenue(money));
    }
}
