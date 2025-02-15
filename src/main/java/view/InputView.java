package view;

import java.util.Scanner;

public class InputView {

    private static final String PURCHASE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private Scanner scanner = new Scanner(System.in);

    public String getPurchaseInput() {
        System.out.println(PURCHASE_INPUT_MESSAGE);
        return scanner.nextLine();
    }

    public String getWinningNumbers() {
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
        return scanner.nextLine();
    }

    public String getBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        return scanner.nextLine();
    }
}
