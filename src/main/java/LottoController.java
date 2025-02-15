import java.io.IOException;
import java.util.List;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private LottoManager lottoManager;

    public LottoController(InputView inputView, OutputView outputView, LottoManager lottoManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoManager = lottoManager;
    }

    public void run() throws IOException {
        Money money = inputView.inputMoney();
        final int purchasableLottoCount = Lotto.countPurchasableLottosByMoney(money);
        outputView.printLottoCount(purchasableLottoCount);

        List<Lotto> lottos = lottoManager.generateLottos(purchasableLottoCount);
        outputView.printLottos(lottos);

        WinningLotto winningLotto = inputView.inputWinningLotto();

        WinningResult winningResult = lottoManager.calculateWinningResult(lottos, winningLotto);
        outputView.printWinningResult(winningResult);
        outputView.printRevenue(lottoManager.calculateRevenue(winningResult, money));
    }
}
