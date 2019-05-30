package lotto.view;

import lotto.view.validator.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String WINNING_NUMBER_DELIMITER = ", ";
    private static Scanner scanner = new Scanner(System.in);

    public static int inputBuyPrice() {
        String input = "";
        do {
            System.out.println("구입금액을 입력해주세요.");
            input = scanner.nextLine();
        } while (!InputValidator.inputValidateBuyPrice(input));
        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinningNumber() {
        String input = "";
        do {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            input = scanner.nextLine();
        } while (!InputValidator.inputValidateWinningNumber(input));
        return Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusBall() {
        int bonusBall = 0;
        do {
            System.out.println("보너스 볼을 입력해 주세요.");
            bonusBall = scanner.nextInt();
        } while (!InputValidator.inputValidateBonusBall(bonusBall));
        return bonusBall;
    }


}
