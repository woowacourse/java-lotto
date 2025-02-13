package view;

import java.util.Scanner;

public class InputView {

    private static final String ENTER_PURCHASE_PRICE = "구입금액을 입력해 주세요.";
    private static final String ENTER_BEFORE_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final Scanner console = new Scanner(System.in);

    public String enterPurchasePrice() {
        return enter(ENTER_PURCHASE_PRICE);
    }

    public String enterWinningNumbers() {
        return enter(ENTER_BEFORE_WINNING_NUMBER);
    }

    public String enterBonusNumber() {
        return enter(ENTER_BONUS_NUMBER);
    }

    private String enter(String message) {
        System.out.println(message);
        return console.nextLine();
    }
}
