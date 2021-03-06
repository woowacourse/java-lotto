package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String ENTER_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ENTER_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String ENTER_MANUAL_LOTTO_TICKETS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String ENTER_WINNER_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoney() {
        System.out.println(ENTER_PURCHASE_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputWinnerTicket() {
        System.out.println();
        System.out.println(ENTER_WINNER_TICKET_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputBonusBall() {
        System.out.println(ENTER_BONUS_BALL_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputManualLottoCount() {
        System.out.println();
        System.out.println(ENTER_MANUAL_LOTTO_COUNT);
        return scanner.nextLine();
    }

    public static String inputTicket() {
        System.out.println();
        System.out.println(ENTER_MANUAL_LOTTO_TICKETS);
        return scanner.nextLine();
    }
}
