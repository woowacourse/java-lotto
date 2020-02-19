package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.PurchaseAmount;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private static PurchaseAmount amount;
    private static List<Lotto> lottoDummy = new ArrayList<>();

    public static void main(String[] args) {

        amount = new PurchaseAmount(InputView.inputPurchaseAmount());
        int lottoCount = amount.calculateCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        for (int index = 0; index < lottoCount; index++){
            lottoDummy.add(LottoFactory.createOneLotto());
        }
        OutputView.printLottoDummy(lottoDummy);
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        OutputView.printResult();
    }
}
