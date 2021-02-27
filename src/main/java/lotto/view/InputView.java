package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String NUMBER_INPUT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String LAST_WEEK_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 로또 번호를 입력해 주세요.";

    public static String requestMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int requestManualCount() {
        System.out.println(MANUAL_LOTTO_COUNT_MESSAGE);
        try {
            return SCANNER.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_INPUT_ERROR);
        }
    }

    public static List<String> requestManualLotto(int count) {
        System.out.println(MANUAL_LOTTO_NUMBER_MESSAGE);
        List<String> lottoNumbers = new ArrayList<>();
        SCANNER.nextLine();
        for (int i=0; i<count; i++) {
            lottoNumbers.add(SCANNER.nextLine());
        }
        return lottoNumbers;
    }

    public static String requestWinningNumber() {
        System.out.println(LAST_WEEK_LOTTO_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String requestBonusBall() {
        System.out.println(BONUS_BALL_MESSAGE);
        return SCANNER.nextLine();
    }
}
