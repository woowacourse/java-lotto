package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PURCHASE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUM_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static long receiveLottoMoney() {
        System.out.println(PURCHASE_MESSAGE);
        return Long.parseLong(SCANNER.nextLine());
    }

    public static List<String> receiveManualLotto() {
        System.out.println(MANUAL_LOTTO_COUNT_MESSAGE);
        int manualLottoCount = Integer.parseInt(SCANNER.nextLine());
        List<String> manualLottos = new ArrayList<>();
        System.out.println(MANUAL_LOTTO_NUM_MESSAGE);
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottos.add(SCANNER.nextLine());
        }
        return manualLottos;
    }

    public static String receiveWinning() {
        System.out.println(WINNING_LOTTO_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int receiveBonus() {
        System.out.println(BONUS_BALL_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
