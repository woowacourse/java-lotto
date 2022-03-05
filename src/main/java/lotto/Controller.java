package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.number.LottoNumberFactory;
import lotto.model.Lottos;
import lotto.model.LottoCart;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PurchaseCountDTO;
import lotto.model.dto.PrizeCountDTO;
import lotto.model.number.LottoNumber;
import lotto.model.prize.PrizeCountMap;
import lotto.model.PurchaseCount;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {
    private final InputView inputView;
    private final ResultView resultView;

    public Controller() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        Money money = askMoneyAmount();
        Lottos lottos = makeLottos(money);
        givePrize(money, lottos);
    }

    private Money askMoneyAmount() {
        return Money.from(inputView.askMoneyAmount());
    }

    private Lottos makeLottos(Money money) {
        LottoCart lottoCart = new LottoCart(PurchaseCount.of(money, inputView.askManualCount()));
        Lottos lottos = purchaseLottos(lottoCart);
        resultView.showPurchaseCount(PurchaseCountDTO.of(lottoCart.getPurchaseCount()));
        resultView.showLottos(LottoDTO.from(lottos));
        return lottos;
    }

    private Lottos purchaseLottos(LottoCart lottoCart) {
        inputView.askManualNumbers();
        purchaseManualLotto(lottoCart);
        lottoCart.addAutoLottos();
        return lottoCart.getLottos();
    }

    private void purchaseManualLotto(LottoCart lottoCart) {
        while (lottoCart.isManualAvailable()) {
            lottoCart.addManualLotto(List.of(inputView.readNumbers()));
        }
    }

    private void givePrize(Money money, Lottos lottos) {
        PrizeCountMap prizeCountMap = PrizeCountMap.from(lottos.match(makeWinningLotto()));
        resultView.showPrizeInformation(PrizeCountDTO.from(prizeCountMap));
        resultView.showEarningRate(prizeCountMap.calculateEarningRate(money));
    }

    private WinningLotto makeWinningLotto() {
        Lotto winningLotto = askWinningNumbers();
        return new WinningLotto(winningLotto, askBonusNumber());
    }

    private Lotto askWinningNumbers() {
        String[] winningNumbersInput = inputView.askWinningNumbers();
        return Lotto.from(List.of(winningNumbersInput));
    }

    private LottoNumber askBonusNumber() {
        String bonusNumberInput = inputView.askBonusNumber();
        return LottoNumberFactory.getNumber(bonusNumberInput);
    }
}
