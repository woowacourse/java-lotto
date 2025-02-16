package controller;

import converter.InputConverter;
import domain.Lotto;
import domain.LottoStore;
import domain.Lottos;
import domain.Money;
import domain.Number;
import domain.WinningLotto;
import domain.WinningResult;
import domain.lottogeneratestrategy.LottoPickStrategy;
import domain.lottogeneratestrategy.RandomLottoPickStrategy;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputConverter inputConverter;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            InputConverter inputConverter
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputConverter = inputConverter;
    }

    public void start() {
        try {
            Money purchaseLottoMoney = inputMoney();
            Lottos purchasedLottos = purchaseLottos(purchaseLottoMoney);
            outputView.printPurchaseLottos(purchasedLottos);

            WinningLotto winningLotto = inputWinningLotto();
            WinningResult winningResult = purchasedLottos.calculateWinning(winningLotto);
            outputView.printWinningResult(winningResult);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Money inputMoney() {
        String rawMoney = inputView.inputMoney();
        int purchaseAmount = inputConverter.convertStringToMoneyValue(rawMoney);
        return Money.forPurchaseAmount(purchaseAmount);
    }

    private Lottos purchaseLottos(Money purchaseLottoMoney) {
        LottoPickStrategy numberStrategy = new RandomLottoPickStrategy();
        LottoStore lottoStore = new LottoStore(Lotto.createLottoMachine(numberStrategy));
        return lottoStore.buy(purchaseLottoMoney);
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningNumbers();
        Number bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningNumbers() {
        String rawWinningNumbers = inputView.inputWinningNumbers();
        List<Number> numbers = inputConverter.convertStringToWinningNumberValue(rawWinningNumbers);
        return new Lotto(numbers);
    }

    private Number inputBonusNumber() {
        String rawBonusNumber = inputView.inputBonusNumber();
        int bonusNumberValue = inputConverter.convertStringToBonusNumberValue(rawBonusNumber);
        return new Number(bonusNumberValue);
    }
}
