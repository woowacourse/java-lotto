package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final String ASK_HOW_MUCH_TO_BUY = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNER_LOTTO_TICKET = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_WINNER_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getMoneyInput() {
        System.out.println(ASK_HOW_MUCH_TO_BUY);
        return getUserInput();
    }

    public static String getWinnerNumbersInput() {
        System.out.println(ASK_WINNER_LOTTO_TICKET);
        return getUserInput();
    }

    public static String getBonusNumberInput() {
        System.out.println(ASK_WINNER_BONUS_NUMBER);
        return getUserInput();
    }

    private static String getUserInput() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
