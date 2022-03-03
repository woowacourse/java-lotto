package view;

import utils.InputValidation;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTOS_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private final static Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        try {
            System.out.println(INPUT_PRICE);
            return Integer.parseInt(InputValidation.validateIsNum(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    public static int inputManualLottosCount() {
        try {
            System.out.println(MANUAL_LOTTOS_COUNT);
            return Integer.parseInt(InputValidation.validateIsNum(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    public static List<Integer> inputWinningLottoNumbers() {
        try {
            System.out.println(INPUT_WINNING_LOTTO_NUMBERS);
            List<String> inputNumbers = InputValidation.validateIsNumsListString(scanner.nextLine());
            return inputNumbers.stream()
                    .map(number -> Integer.parseInt(number))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputWinningLottoNumbers();
        }
    }

    public static void showMessageInputLottoNumbers() {
        System.out.println(INPUT_LOTTO_NUMBERS);
    }

    public static List<Integer> inputLottoNumbers() {
        try {
            List<String> inputNumbers = InputValidation.validateIsNumsListString(scanner.nextLine());
            return inputNumbers.stream()
                    .map(number -> Integer.parseInt(number))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputLottoNumbers();
        }
    }

    public static int inputBonus() {
        try {
            System.out.println(INPUT_BONUS_NUMBER);
            String input = InputValidation.validateBallInput(scanner.nextLine());
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBonus();
        }
    }

}
