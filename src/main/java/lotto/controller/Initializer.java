package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Initializer {

    private static final int PAYMENT_UNIT = 1000;

    public static void initialize() {
        initializeLottoNumbers();
        initializePayment();
        initializeAutoTickets(Payment.payment / PAYMENT_UNIT);
        initializeWinNumber();
        initializeBonusBallNumber();
    }

    private static void initializeLottoNumbers() {
        new LottoNumbers();
    }

    private static void initializePayment() {
        OutputView.printInput();
        new Payment(InputView.input());
    }

    private static void initializeAutoTickets(int tryCount) {
        OutputView.printHowManyTicketsPurchase(tryCount);
        new AutoTickets(tryCount);
        OutputView.printAutoNumbers(AutoTickets.getAutoTickets());
    }

    private static void initializeWinNumber() {
        OutputView.printInputWinNumber();
        new WinNumber(InputView.input());
    }

    private static void initializeBonusBallNumber() {
        OutputView.printInputBonusNumber();
        new BonusBall(InputView.input());
    }
}
