package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_MONEY_ONLY_NUMBER_MESSAGE = "[ERROR] 숫자를 입력해주세요.";
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String DELIMITER = ", ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int askInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = scanner.nextLine();
        return convertToInt(input);
    }

    public static LottoNumber askInputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        String input = scanner.nextLine();
        int bonusBall = convertToInt(input);
        return LottoNumber.of(bonusBall);
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_MONEY_ONLY_NUMBER_MESSAGE);
        }
    }

    public static Lotto askInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String input = scanner.nextLine();
        String[] numbers = input.split(DELIMITER);
        Set<LottoNumber> collect = Arrays.stream(numbers)
                .map(Integer::parseInt)
                .map(LottoNumber::of)
                .collect(Collectors.toSet());
        return new Lotto(collect);
    }

    public static int askManualLottoCount(int maxLottoCount) {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT);
        int manualLottoCount = convertToInt(scanner.nextLine());

        return manualLottoCount;
    }

    private static void validateCountRange(int maxLottoCount, int inputCount) {
        if (inputCount < 0 || inputCount > maxLottoCount) {
            throw new IllegalArgumentException("구매할 로또 수를 다시 입력해주세요.");
        }
    }
}