package lotto.view;

import lotto.domain.ManualCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String ASK_HOW_MUCH_TO_BUY = "구입금액을 입력해 주세요.";
    private static final String ASK_BUY_MANUAL_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String ASK_BUY_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String ASK_WINNER_LOTTO_TICKET = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_WINNER_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getMoneyInput() {
        System.out.println(ASK_HOW_MUCH_TO_BUY);
        return getUserInput();
    }

    public static String getManualCountInput() {
        System.out.println(ASK_BUY_MANUAL_LOTTO);
        return getUserInput();
    }

    public static List<String> getManualNumbersInput(ManualCount manualCount) {
        System.out.println(ASK_BUY_MANUAL_NUMBERS);
        return IntStream.range(0, manualCount.getValue())
                .mapToObj(i -> getUserInput())
                .collect(Collectors.toList());
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
