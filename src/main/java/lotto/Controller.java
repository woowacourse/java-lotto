package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoWheel;
import lotto.model.Lottos;
import lotto.model.LottosBuilder;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.LottosDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.LottoNumber;
import lotto.model.prize.PrizeInformations;
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
        LottosBuilder lottosBuilder = LottosBuilder.of(money, inputView.askManualCount());
        Lottos lottos = purchaseLottos(lottosBuilder);
        resultView.showPurchaseCount(LottosDTO.of(lottos));
        resultView.showLottos(LottoDTO.from(lottos));
        return lottos;
    }

    private Lottos purchaseLottos(LottosBuilder lottosBuilder) {
        inputView.askManualNumbers();
        purchaseManualLotto(lottosBuilder);
        lottosBuilder.addAutoLottos();
        return lottosBuilder.toLottos();
    }

    private void purchaseManualLotto(LottosBuilder lottosBuilder) {
        while (lottosBuilder.isManualAvailable()) {
            lottosBuilder.addManualLotto(List.of(inputView.readNumbers()));
        }
    }

    private void givePrize(Money money, Lottos lottos) {
        PrizeInformations prizeInformations = PrizeInformations.from(lottos.match(makeWinningLotto()));
        resultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));
        resultView.showEarningRate(prizeInformations.calculateEarningRate(money));
    }

    private WinningLotto makeWinningLotto() {
        Lotto winningLotto = askWinningNumbers();
        return new WinningLotto(winningLotto, askBonusNumber());
    }

    private Lotto askWinningNumbers() {
        String[] winningNumbersInput = inputView.askWinningNumbers();
        return LottoWheel.from(List.of(winningNumbersInput));
    }

    private LottoNumber askBonusNumber() {
        String bonusNumberInput = inputView.askBonusNumber();
        return LottoWheel.getNumber(bonusNumberInput);
    }
}
