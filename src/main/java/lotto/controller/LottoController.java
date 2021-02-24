package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.model.LottoResult;
import lotto.model.LottoResults;
import lotto.model.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        Money insertedMoney = Money.priceOf(InputView.inputPurchaseAmount());
        LottoMachine lottoMachine = LottoMachine.insertMoney(insertedMoney);
        Lottos manualLottos = lottoMachine.buyManualLottos(InputView.inputManualLottoNumbers());
        Lottos automaticLottos = lottoMachine.buyAutomaticLottos();
        OutputView.printLottoPurchaseResult(manualLottos, automaticLottos);

        Lotto winningLottoNumber = Lotto.of(InputView.inputWinningLottoNumbers());
        int bonus = InputView.inputWinningBonus();
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
        LottoResult manualLottosResult = manualLottos.match(winningLotto);
        LottoResult automaticLottosResult = automaticLottos.match(winningLotto);
        LottoResults lottoResults = LottoResults.of(manualLottosResult, automaticLottosResult);
        OutputView.printLottoResult(lottoResults);
    }
}


