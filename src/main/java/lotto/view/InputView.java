package lotto.view;

import lotto.game.LottoCount;
import lotto.money.Money;
import lotto.ticket.BonusBall;
import lotto.ticket.WinnerTicket;

import java.util.ArrayList;
import java.util.List;
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

    public static LottoCount inputManualTicketAmount() {
        String value = scanner.nextLine();
        return new LottoCount(value);
    }

    public static List<String> inputNumbers(LottoCount count) {
        List<String> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < count.getLottoCount(); i++) {
            manualLottoNumbers.add(scanner.nextLine());
        }
        return manualLottoNumbers;
    }
}
