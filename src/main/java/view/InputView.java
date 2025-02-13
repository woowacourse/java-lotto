package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    public String inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return readLine();
    }

    public String inputWinningNumbers() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return readLine();
    }

    public String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        return readLine();
    }

    private String readLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("입력중 예외가 발생했습니다.", e);
        }
    }
}
