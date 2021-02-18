package lotto.view;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.WinnerTicket;
import lotto.money.Money;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Money inputMoney() {
        String value = scanner.nextLine();
        return new Money(value);
    }

    public static WinnerTicket inputWinnerTicket() {
        String value = scanner.nextLine();
        return new WinnerTicket(value);
    }

    public static BonusBall inputBonusBall(WinnerTicket winnerTicket) {
        String value = scanner.nextLine();
        return new BonusBall(value, winnerTicket);
    }
}
