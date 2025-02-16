package controller;

import converter.StringToLottoConverter;
import converter.StringToLottoNumberConverter;
import converter.StringToMoneyConverter;
import domain.Lotto;
import domain.LottoNumber;
import domain.LottoStore;
import domain.Lottos;
import domain.Money;
import domain.WinningLotto;
import domain.WinningResult;
import domain.numbergenerator.NumberGenerator;
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
            outputView.printPurchaseLottos(purchasedLottos.getLottos());

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
        NumberGenerator numberGenerator = LottoNumber.createRandomNumberGenerator();
        LottoStore lottoStore = new LottoStore(Lotto.createLottoMachine(numberGenerator));
        return lottoStore.buy(purchaseLottoMoney);
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningLottoNumbers();
        LottoNumber bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningLottoNumbers() {
        String rawWinningLottoNumbers = inputView.inputWinningLottoNumbers();
        return new StringToLottoConverter().convert(rawWinningLottoNumbers);
    }

    private LottoNumber inputBonusNumber() {
        String rawBonusNumber = inputView.inputBonusNumber();
        return new StringToLottoNumberConverter().convert(rawBonusNumber);
    }
}
