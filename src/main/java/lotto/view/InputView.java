package lotto.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private static final String INVALID_INPUT_MONEY_EXCEPTION_MESSAGE = "구입 금액은 숫자여야합니다.";
    private static final String INVALID_INPUT_MANUAL_LOTTO_COUNT_EXCEPTION_MESSAGE
        = "수동 로또 개수는 숫자여야합니다.";
    private static final String INVALID_INPUT_WINNING_BONUS_BALL_EXCEPTION_MESSAGE
        = "보너스 볼은 숫자여야 합니다.";

    private InputView() {
    }

    public static int inputMoney() {
        try {
            System.out.println(INPUT_MONEY_MESSAGE);
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException(INVALID_INPUT_MONEY_EXCEPTION_MESSAGE);
        }
    }

    public static int inputManualLottoCount() {
        try {
            System.out.println(INPUT_MANUAL_COUNT_MESSAGE);
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException(INVALID_INPUT_MANUAL_LOTTO_COUNT_EXCEPTION_MESSAGE);
        }
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
        try {
            System.out.println(WINNING_LOTTO_BONUS_BALL_MESSAGE);
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException(INVALID_INPUT_WINNING_BONUS_BALL_EXCEPTION_MESSAGE);
        }
    }
}
