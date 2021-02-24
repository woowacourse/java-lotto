package lotto.view;

import static lotto.utils.ConsoleUtils.printNotice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getMoneyInput() {
        printNotice("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getWinningNumbersInput() {
        printNotice("지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getBonusBallInput() {
        printNotice("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getManualBuyAmountInput() {
        printNotice("수동으로 구매할 로또 수를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static List<String> getManualLottoTicketsInput(BigInteger manuallyBuyCount) {
        printNotice("수동으로 구매할 번호를 입력해 주세요.");

        List<String> lottoTicketsInput = new ArrayList<>();

        for (BigInteger ticketAmount = manuallyBuyCount;
                ticketAmount.compareTo(BigInteger.ZERO) > 0;
                ticketAmount = ticketAmount.subtract(BigInteger.ONE)) {

            lottoTicketsInput.add(SCANNER.nextLine());
        }

        return lottoTicketsInput;
    }
}
