package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Lotto {
    public static void lotto() {
        Money money = setMoney();
        AutoTickets autoTickets = getAutoTickets(money.getMoney() / 1000);
        WinNumbers winNumbers = getWinNumbers();
        BonusBallNo bonusBallNo = getBonusBallNo(winNumbers);

        autoTickets.matchNumberResult(winNumbers, bonusBallNo);

        printCorrectResults();
        OutputView.printYield(money.getYield());
    }

    private static Money setMoney() {
        OutputView.printInput();
        return new Money(InputView.input());
    }

    private static AutoTickets getAutoTickets(int ticketsCount) {
        new LottoNumbers();
        OutputView.printHowManyTicketsPurchase(ticketsCount);
        AutoTickets autoTickets = new AutoTickets(ticketsCount);
        OutputView.printAutoNumbers(autoTickets.getAutoTickets());
        return autoTickets;
    }

    private static WinNumbers getWinNumbers() {
        OutputView.printInputWinNumber();
        return new WinNumbers(InputView.input());
    }

    private static BonusBallNo getBonusBallNo(WinNumbers winNumbers) {
        OutputView.printInputBonusNumber();
        return new BonusBallNo(InputView.input(), winNumbers);
    }

    private static void printCorrectResults() {
        OutputView.printResult();
        for (LottoResult lottoResult : LottoResult.values()) {
            OutputView.printCorrectResult(lottoResult.getCorrect(), lottoResult.getPrize(),
                lottoResult.getCount());
        }
    }
}