package lotto.controller;

import lotto.Money;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoStore;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.NumManualLotto;
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public LottoController() {
    }

    public void play() {
        try {
            Lottos purchasedLottos = createLottos();
            WinningLotto winningLotto = createWinningLotto();
            compareLotto(purchasedLottos, winningLotto);
        } catch (Exception error) {
            OutputView.errorPrint(error);
            play();
        }
    }

    private WinningLotto createWinningLotto() {
        Lotto winningLotto = createWinningNumbers();
        Integer bonusNumber = createBonusNUmber();
        return WinningLotto.generate(winningLotto,
            LottoNumber.valueOf(bonusNumber));
    }

    private void compareLotto(Lottos purchasedLottos, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();

        purchasedLottos.check(lottoResult, winningLotto);

        OutputView.totalWinning();
        OutputView.numMatchPrint(lottoResult);
        OutputView.profitRatePrint(lottoResult);
    }

    private Lottos createLottos() {
        LottoStore lottoStore = new LottoStore();
        Money money = startMoney();
        NumManualLotto numManualLotto = buyManualLotto();
        validNumManualLotto(money, numManualLotto);
        Lottos purchasedLottos = lottoStore.buyLottos(money, numManualLotto);
        OutputView
            .numPurchasedLotto(getNumManualLotto(numManualLotto), purchasedLottos.getNumAutoLotto(
                numManualLotto));
        OutputView.lottosPrint(purchasedLottos);
        return purchasedLottos;
    }

    private Integer getNumManualLotto(NumManualLotto numManualLotto) {
        return numManualLotto.getNumLotto();
    }


    private void validNumManualLotto(Money money, NumManualLotto numManual) {
        money.validNumManual(numManual);
    }

    private NumManualLotto buyManualLotto() {
        OutputView.inputNumManualLotto();
        return InputView.inputNumManualLotto();
    }

    private Money startMoney() {
        OutputView.inputMoney();
        return InputView.inputMoney();
    }


    private Lotto createWinningNumbers() {
        OutputView.inputWinningNumber();
        String winningNumbers = InputView.inputWinningNumbers();
        return Lotto.manual(winningNumbers);
    }

    private Integer createBonusNUmber() {
        OutputView.inputBonus();
        String bonusNumber = InputView.inputBonusNumber();
        return Integer.parseInt(bonusNumber);
    }
}


