package controller;

import model.Lotto;
import model.Lottos;
import model.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;

public class App {
    public static void main(String[] argc) {
        new WinningNumbers();
        final int totalPurchaseAmount = InputView.inputAmountOfMoney().getLottoPurchaseAmount();
        final int manualPurchaseAmount = InputView.inputAmountOfManualPicks(totalPurchaseAmount);
        final int autoPurchaseAmount = totalPurchaseAmount - manualPurchaseAmount;
        final List<Lotto> manualLottos = InputView.inputManualLottoNumbers(manualPurchaseAmount);
        OutputView.printPurchaseAmount(manualPurchaseAmount, autoPurchaseAmount);
        final Lottos lottos = new Lottos(manualLottos, autoPurchaseAmount);
        OutputView.printLottos(lottos);
        OutputView.printResult(lottos.getResult());
    }
}