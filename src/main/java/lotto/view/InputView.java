package lotto.view;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {

    }

    public static String inputMoney() throws IOException {
        out.println("구입금액을 입력해 주세요.");
        return reader.readLine();
    }

    public static String inputWinningNumbers() throws IOException {
        out.println("지난 주 당첨 번호를 입력해 주세요.");
        return reader.readLine();
    }

    public static String inputBonusBall() throws IOException {
        out.println("보너스 볼을 입력해 주세요.");
        return reader.readLine();
    }
}
