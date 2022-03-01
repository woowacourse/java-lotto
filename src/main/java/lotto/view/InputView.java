package lotto.view;

import java.util.Scanner;

public class InputView {
    private final String MESSAGE_MONEY_AMOUNT = "구입금액을 입력해 주세요.";
    public final String MESSAGE_MANUAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private final String MESSAGE_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private final String MESSAGE_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    public final String MESSAGE_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    public final String WINNING_NUMBER_DELIMITER = ",";

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public String askMoneyAmount() {
        System.out.println(MESSAGE_MONEY_AMOUNT);
        return scanner.nextLine();
    }

    public String askManualCount() {
        System.out.println(MESSAGE_MANUAL_COUNT);
        return scanner.nextLine();
    }

    public void askManualNumbers() {
        System.out.println(MESSAGE_MANUAL_NUMBERS);
    }

    public String[] readNumbers() {
        return splitByComma(scanner.nextLine());
    }

    public String[] askWinningNumbers() {
        System.out.println(MESSAGE_WINNING_NUMBERS);
        return splitByComma(scanner.nextLine());
    }

    public String askBonusNumber() {
        System.out.println(MESSAGE_BONUS_NUMBER);
        return scanner.nextLine();
    }

    private String[] splitByComma(String string) {
        return string.split(WINNING_NUMBER_DELIMITER);
    }
}
