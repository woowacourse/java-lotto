package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    public String inputMoney() {
        System.out.println(PURCHASE_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public String inputWinningLotto() {
        System.out.println(WINNING_LOTTO_MESSAGE);
        return scanner.nextLine();
    }

    public String inputBonusNumber() {
        System.out.println(BONUS_BALL_MESSAGE);
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

}
