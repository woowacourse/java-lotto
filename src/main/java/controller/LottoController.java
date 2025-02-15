package controller;

import converter.StringToLottoConverter;
import converter.StringToMoneyConverter;
import converter.StringToNumberConverter;
import domain.Lotto;
import domain.LottoMachine;
import domain.LottoStore;
import domain.Lottos;
import domain.Money;
import domain.Number;
import domain.WinningLotto;
import domain.WinningResult;
import domain.numberstrategy.NumberPickStrategy;
import domain.numberstrategy.RandomNumberPickStrategy;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            Money purchaseLottoMoney = inputMoney();
            Lottos purchasedLottos = purchaseLottos(purchaseLottoMoney);
            outputView.printPurchaseLottos(purchasedLottos);

            WinningLotto winningLotto = inputWinningLotto();
            WinningResult winningResult = purchasedLottos.calculateWinning(winningLotto, purchaseLottoMoney);
            outputView.printWinningResult(winningResult);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Money inputMoney() {
        String rawMoney = inputView.inputMoney();
        return new StringToMoneyConverter().convert(rawMoney);
    }

    private Lottos purchaseLottos(Money purchaseLottoMoney) {
        NumberPickStrategy numberStrategy = new RandomNumberPickStrategy();
        LottoStore lottoStore = new LottoStore(new LottoMachine(numberStrategy));
        return lottoStore.buy(purchaseLottoMoney);
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningLottoNumbers();
        Number bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningLottoNumbers() {
        String rawWinningLottoNumbers = inputView.inputWinningLottoNumbers();
        return new StringToLottoConverter().convert(rawWinningLottoNumbers);
    }

    private Number inputBonusNumber() {
        String rawBonusNumber = inputView.inputBonusNumber();
        return new StringToNumberConverter().convert(rawBonusNumber);
    }
}
