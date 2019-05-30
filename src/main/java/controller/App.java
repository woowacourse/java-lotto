package controller;

import model.Lotto;
import model.LottoNumber;
import model.Lottos;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] argc) {
        final int totalPurchaseAmount = InputView.inputAmountOfMoney().getLottoPurchaseAmount();
        final int manualPurchaseAmount = InputView.inputAmountOfManualPicks(totalPurchaseAmount);
        final int autoPurchaseAmount = totalPurchaseAmount - manualPurchaseAmount;
        final List<Lotto> manualLottos = InputView.inputManualLottoNumbers(manualPurchaseAmount);
        OutputView.printPurchaseAmount(manualPurchaseAmount, autoPurchaseAmount);
        final Lottos lottos = new Lottos(manualLottos, autoPurchaseAmount);
        OutputView.printLottos(lottos);
        /*
        final Set<LottoNumber> winningNumbers = InputView.inputWinningNumbers();
        final LottoNumber bonusNumber = InputView.inputBonusNumber(winningNumbers);
        OutputView.printResult(lottos.getResult(winningNumbers, bonusNumber));
        */
        OutputView.printResult(lottos.getResult());
    }
}