package lotto.controller;

import java.util.List;
import java.util.Set;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.InputViewExceptionHandler;
import lotto.view.OutputView;

public class LottoGame {

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        Lottos lottosManual = purchaseLottosManually(purchaseAmount);
        Lottos lottosAutomatic = LottoPlayer.purchaseLottosAutomatically(purchaseAmount, lottosManual.size());
        OutputView.printLottos(lottosManual, lottosAutomatic);
        Result result = produceResult(purchaseAmount, lottosManual, lottosAutomatic);
        OutputView.printResult(result);
    }

    private Money inputPurchaseAmount() {
        int input = InputViewExceptionHandler.handle(InputView::inputPurchaseAmount);
        return NonPlayerCharacter.translateToMoney(input);
    }

    private Lottos purchaseLottosManually(Money purchaseAmount) {
        int numberToBuyLottoManually = InputViewExceptionHandler.handle(() -> InputView.inputNumberToBuyManually(purchaseAmount.toLottosSize()));
        List<Set<Integer>> numbersBasket = InputViewExceptionHandler.handle(() -> InputView.inputnumbersBasketManually(numberToBuyLottoManually));
        List<Set<LottoNumber>> lottoNumbersBasket = NonPlayerCharacter.translateToLottoNumbersBasket(numbersBasket);
        return LottoPlayer.purchaseLottosManually(lottoNumbersBasket);
    }

    private Result produceResult(Money purchaseAmount, Lottos lottosManual, Lottos lottosAutomatic) {
        Lotto winningLotto = declareWinningLotto();
        int bonusNumber = InputViewExceptionHandler.handle(InputView::inputBonusNumber);
        return NonPlayerCharacter.judgeResult(lottosManual, lottosAutomatic, winningLotto, bonusNumber, purchaseAmount);
    }

    private Lotto declareWinningLotto() {
        List<Integer> winningNumbers = InputViewExceptionHandler.handle(InputView::inputLastWeekWinningNumbers);
        return NonPlayerCharacter.declareWinningLotto(winningNumbers);
    }
}
