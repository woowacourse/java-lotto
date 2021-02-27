package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottosDto;
import lotto.domain.lottoresult.LottoResults;
import lotto.domain.lottoresult.LottoResultsDto;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        try {
            Money insertedMoney = Money.priceOf(InputView.inputPurchaseAmount());
            LottoMachine lottoMachine = LottoMachine.insertMoney(insertedMoney);
            int numOfManualLotto = InputView.inputNumOfManualLotto();
            Lottos manualLottos = lottoMachine
                .buyManualLottos(InputView.inputManualLottoNumbers(numOfManualLotto));
            Lottos automaticLottos = lottoMachine.buyAutomaticLottos();
            OutputView.printLottoPurchaseResult(LottosDto.from(manualLottos),
                LottosDto.from(automaticLottos));

            WinningLotto winningLotto = inputWinningLotto();
            LottoResults lottoResults = LottoResults
                .of(manualLottos.match(winningLotto), automaticLottos.match(winningLotto));
            OutputView.printLottoResult(LottoResultsDto.from(lottoResults));
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            play();
        }
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningLottoNumber = InputView.inputWinningLottoNumbers();
        int bonus = InputView.inputWinningBonus();
        return new WinningLotto(winningLottoNumber, bonus);
    }
}


