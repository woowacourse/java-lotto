package lotto.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요";
    private static final String INPUT_BONUS_BALL_NUMBER = "보너스 볼을 입력해주세요";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MISMATCH_ERROR_MESSAGE = "[ERROR] 숫자를 입력해주세요.";

    public static int inputMoney() {
        OutputView.printInputMessage(INPUT_MONEY);
        int money;
        try {
            money = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new IllegalArgumentException(INPUT_MISMATCH_ERROR_MESSAGE);
        }
        scanner.nextLine();
        return money;
    }

    public static int inputManualLottoCount() {
        OutputView.printInputMessage(INPUT_MANUAL_LOTTO_NUMBER);
        int manualLottoCount;
        try {
            manualLottoCount = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new IllegalArgumentException(INPUT_MISMATCH_ERROR_MESSAGE);
        }
        scanner.nextLine();
        return manualLottoCount;
    }

    public static List<String> inputManualLottoNumbers(int manualLottoCount) {
        OutputView.printInputMessage(INPUT_MANUAL_LOTTO_NUMBERS);

        return Stream.generate(scanner::nextLine)
                .limit(manualLottoCount)
                .collect(Collectors.toList());
    }

    public static String inputWinningNumbers() {
        OutputView.printInputMessage(INPUT_WINNING_NUMBERS);
        return scanner.nextLine();
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_BALL_NUMBER);
        int bonusNumber;
        try {
            bonusNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new IllegalArgumentException(INPUT_MISMATCH_ERROR_MESSAGE);
        }
        return bonusNumber;
    }
}