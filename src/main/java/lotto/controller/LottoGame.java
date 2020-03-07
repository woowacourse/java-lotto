package lotto.controller;

import java.util.List;
import java.util.Set;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottosManual = purchaseLottosManually(purchaseAmount);
        List<Lotto> lottosAutomatic = Player.purchaseLottosAutomatically(purchaseAmount, lottosManual.size());
        OutputView.printLottos(lottosManual, lottosAutomatic);
        Result result = produceResult(purchaseAmount, lottosManual, lottosAutomatic);
        OutputView.printResult(result);
    }

    private Money inputPurchaseAmount() {
        int input = LottoLogicExecutor.executeOrRepeatWithException(InputView::inputPurchaseAmount);
        return NonPlayerCharacter.translateToMoney(input);
    }

    private List<Lotto> purchaseLottosManually(Money purchaseAmount) {
        int numberToBuyLottoManually = LottoLogicExecutor.executeOrRepeatWithException(() -> InputView.inputNumberToBuyManually(purchaseAmount.toLottosSize()));
        List<Set<Integer>> numbersBasket = LottoLogicExecutor.executeOrRepeatWithException(() -> InputView.inputnumbersBasketManually(numberToBuyLottoManually));
        List<Set<LottoNumber>> lottoNumbersBasket = NonPlayerCharacter.translateToLottoNumbersBasket(numbersBasket);
        return Player.purchaseLottosManually(lottoNumbersBasket);
    }

    private Result produceResult(Money purchaseAmount, List<Lotto> lottosManual, List<Lotto> lottosAutomatic) {
        Lotto winningLotto = declareWinningLotto();
        int bonusNumber = LottoLogicExecutor.executeOrRepeatWithException(InputView::inputBonusNumber);
        return NonPlayerCharacter.judgeResult(lottosManual, lottosAutomatic, winningLotto, bonusNumber, purchaseAmount);
    }

    private Lotto declareWinningLotto() {
        List<Integer> winningNumbers = LottoLogicExecutor.executeOrRepeatWithException(InputView::inputLastWeekWinningNumbers);
        return NonPlayerCharacter.declareWinningLotto(winningNumbers);
    }
}
