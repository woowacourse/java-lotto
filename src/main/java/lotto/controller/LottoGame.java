package lotto.controller;
import java.util.List;
import java.util.Set;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    private NonPlayerCharacter nonPlayerCharacter;
    private Player player;

    public LottoGame(NonPlayerCharacter nonPlayerCharacter, Player player) {
        this.nonPlayerCharacter = nonPlayerCharacter;
        this.player = player;
    }

    public void play() {

        int inputPurchaseAmount = LottoLogicExecutor.executeOrRepeatWithException(InputView::inputPurchaseAmount);
        Money purchaseAmount = nonPlayerCharacter.translateToMoney(inputPurchaseAmount);

        int numberToBuyLottoManually = LottoLogicExecutor.executeOrRepeatWithException(() -> InputView.inputNumberToBuyManually(purchaseAmount.toLottosSize()));
        List<Set<Integer>> numbersBasket = LottoLogicExecutor.executeOrRepeatWithException(() -> InputView.inputnumbersBasketManually(numberToBuyLottoManually));
        List<Set<LottoNumber>> lottoNumbersBasket = nonPlayerCharacter.translateToLottoNumbersBasket(numbersBasket);


        List<Lotto> lottosManual = player.purchaseLottosManually(lottoNumbersBasket);
        List<Lotto> lottosAutomatic = player.purchaseLottosAutomatically(purchaseAmount, lottosManual.size());
        OutputView.printLottos(lottosManual, lottosAutomatic);

        List<Integer> winningNumbers = LottoLogicExecutor.executeOrRepeatWithException(InputView::inputLastWeekWinningNumbers);
        Lotto winningLotto = nonPlayerCharacter.declareWinningLotto(winningNumbers);
        int bonusNumber = LottoLogicExecutor.executeOrRepeatWithException(InputView::inputBonusNumber);
        Result result = nonPlayerCharacter.judgeResult(lottosManual,lottosAutomatic, winningLotto, bonusNumber, purchaseAmount);
        OutputView.printResult(result);
    }
}
