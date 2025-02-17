import domain.Lotto;
import domain.LottoManager;
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
    private final LottoManager lottoManager;

    public LottoController(InputView inputView, OutputView outputView, LottoManager lottoManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoManager = lottoManager;
    }

    public void run() throws IOException {
        Money money = inputView.inputMoney();
        final int purchasableLottoCount = Lotto.countPurchasableLottosByMoney(money);
        outputView.printLottoCount(purchasableLottoCount);

        LottoWallet lottoWallet = lottoManager.generateLottos(purchasableLottoCount);
        outputView.printLottos(lottoWallet);

        WinningLotto winningLotto = inputView.inputWinningLotto();

        WinningResult winningResult = lottoManager.calculateWinningResult(lottoWallet, winningLotto);
        outputView.printWinningResult(winningResult);
        outputView.printRevenue(lottoManager.calculateRevenue(winningResult, money));
    }
}
