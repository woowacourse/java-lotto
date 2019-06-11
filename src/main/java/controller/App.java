package controller;

import model.*;

import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;


public class App {
    public static void main(String[] argc) {
        final WinningNumbers winningNumbers = WinningNumbersFactory.of();
        final LottoPurchaseAmount purchaseAmount = inputAmountOfManualPicks(inputAmountOfMoney());
        final List<Lotto> manualLottos = inputManualLottoNumbers(purchaseAmount);
        printPurchaseAmount(purchaseAmount);
        final Lottos lottos = new Lottos(manualLottos, purchaseAmount);
        printLottos(lottos);
        printWinningNumbers(winningNumbers);
        printResult(lottos.getResult(winningNumbers));
    }
}