package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lotto.util.InputValidator;

public class InputView {

    private static final String LINE = System.lineSeparator();

    public String readPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return getInput();
    }

    public String readWinningLottoNumbers() {
        System.out.println(LINE + "지난 주 당첨 번호를 입력해 주세요.");
        return getInput();
    }

    public String readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getInput();
    }

    private String getInput() {
        String input = readLine();
        InputValidator.validate(input);
        return input;
    }

    private String readLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
