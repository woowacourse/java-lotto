package controller;

import converter.InputConverter;
import domain.Lotto;
import domain.LottoStore;
import domain.Lottos;
import domain.Money;
import domain.Number;
import domain.WinningLotto;
import domain.WinningResult;
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
            LottoStore lottoStore = new LottoStore(Number.createRandomLottoPickStrategy());
            Money purchaseLottoMoney = inputMoney(lottoStore);
            Lottos purchasedLottos = lottoStore.buy(purchaseLottoMoney);
            outputView.printPurchaseLottos(purchasedLottos);

            WinningLotto winningLotto = inputWinningLotto();
            WinningResult winningResult = purchasedLottos.calculateWinning(winningLotto);
            outputView.printWinningResult(winningResult);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Money inputMoney(LottoStore lottoStore) {
        String rawMoney = inputView.inputMoney();
        int purchaseAmount = inputConverter.convertStringToMoneyValue(rawMoney);
        return Money.forPurchaseAmount(purchaseAmount, lottoStore);
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningNumbers();
        Number bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningNumbers() {
        String rawWinningNumbers = inputView.inputWinningNumbers();
        List<Integer> numbers = inputConverter.convertStringToWinningNumberValue(rawWinningNumbers);
        return Lotto.createWinningLotto(numbers);
    }

    private Number inputBonusNumber() {
        String rawBonusNumber = inputView.inputBonusNumber();
        int bonusNumberValue = inputConverter.convertStringToBonusNumberValue(rawBonusNumber);
        return new Number(bonusNumberValue);
    }
}
