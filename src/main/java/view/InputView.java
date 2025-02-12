package view;

import java.util.Scanner;

public class InputView {
    private static final String ENTER_PURCHASE_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ENTER_BONUS_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public String enterPurchasePrice() {
        return enter(ENTER_PURCHASE_PRICE_MESSAGE);
    }

    public String enterWinningNumbers() {
        return enter(ENTER_BONUS_NUMBER_MESSAGE);
    }

}
