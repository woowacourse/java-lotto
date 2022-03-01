package view;

import utils.InputValidation;

import java.util.Scanner;
import java.util.Set;

public class InputView {

    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private final static Scanner scanner = new Scanner(System.in);

    public static int inputPrice() {
        try {
            System.out.println(INPUT_PRICE);
            return InputValidation.validatePrice(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPrice();
        }
    }

    public static Set<Integer> inputWinningLottoNumbers() {
        try {
            System.out.println(INPUT_WINNING_LOTTO_NUMBERS);
            return InputValidation.validateWinningNumber(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputWinningLottoNumbers();
        }
    }

    public static int inputBonus() {
        try {
            System.out.println(INPUT_BONUS_NUMBER);
            return InputValidation.validateBonusNumber(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBonus();
        }
    }

}
