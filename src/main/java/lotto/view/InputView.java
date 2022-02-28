package lotto.view;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public enum InputView {

    INSTANCE;

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    public interface IndividualInput<T> {
        T get() throws IOException, IllegalArgumentException;
    }

    public  <T> T commonInputProcess(IndividualInput<T> individualInputs) {
        try {
            return individualInputs.get();
        } catch (IOException | IllegalArgumentException e) {
            out.println(e.getMessage());
            return commonInputProcess(individualInputs);
        }
    }

    public String inputMoney() throws IOException {
        out.println(INPUT_MONEY_MESSAGE);
        return reader.readLine();
    }

    public String inputWinningNumbers() throws IOException {
        out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return reader.readLine();
    }

    public String inputBonusBall() throws IOException {
        out.println(INPUT_BONUS_BALL_MESSAGE);
        return reader.readLine();
    }
}
