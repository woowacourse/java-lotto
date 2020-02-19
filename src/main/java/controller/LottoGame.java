package controller;

import domain.Lotto;
import domain.PurchaseAmount;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGame {
    private static PurchaseAmount amount;
    private static List<Lotto> lottoDummy;
    private static WinningNumber winningNumber;

    public static void main(String[] args) {

        amount = new PurchaseAmount(InputView.inputPurchaseAmount());
        OutputView.printPurchaseCountMessage(amount.calculateCount());
        
    }
}
