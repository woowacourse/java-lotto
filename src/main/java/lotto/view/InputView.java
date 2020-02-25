package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.LottoCount;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_COUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String WINNING_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String WINNING_LOTTO_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int inputManualCount() {
        System.out.println(INPUT_MANUAL_COUNT_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<String> inputManualLottos(LottoCount lottoCount) {
        System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
        List<String> manualLottos = new ArrayList<>();
        int manualLottoCount = lottoCount.getManualLottoCount();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottos.add(SCANNER.nextLine());
        }
        return manualLottos;
    }

    public static String inputWinningLotto() {
        System.out.println(WINNING_LOTTO_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int inputWinningBonusBall() {
        System.out.println(WINNING_LOTTO_BONUS_BALL_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
