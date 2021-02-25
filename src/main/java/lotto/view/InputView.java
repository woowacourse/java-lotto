package lotto.view;

import lotto.lottogame.LottoCount;
import lotto.lottogame.ManualLottoCount;
import lotto.lottoticket.BonusBall;
import lotto.lottoticket.WinnerTicket;
import lotto.money.Money;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Money inputMoney() {
        return new Money(scanner.nextLine());
    }

    public static WinnerTicket inputWinnerTicket() {
        return new WinnerTicket(scanner.nextLine());
    }

    public static BonusBall inputBonusBall(WinnerTicket winnerTicket) {
        return new BonusBall(scanner.nextLine(), winnerTicket);
    }

    public static ManualLottoCount inputManualLottoCount(LottoCount lottoCount) {
        return new ManualLottoCount(scanner.nextLine(), lottoCount);
    }

    public static String inputTicket() {
        return scanner.nextLine();
    }
}
