package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_FORMAT_EXCEPTION_MESSAGE = "입력 조건에 맞지 않습니다. 다시 입력해주세요.";

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        try {
            String input = SCANNER.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(INPUT_FORMAT_EXCEPTION_MESSAGE);
            return inputMoney();
        }
    }

    public static int inputAmountOfCustom() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        try {
            String input = SCANNER.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(INPUT_FORMAT_EXCEPTION_MESSAGE);
            return inputAmountOfCustom();
        }
    }

    public static void printCustomLottoNumbersMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printWinningLottoNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static String inputLottoNumbers() {
        try {
            String input = SCANNER.nextLine();

            validateNullInputException(input);
            validateEmptyInputException(input);

            return input.replaceAll(" ", "");
        } catch (NullPointerException e) {
            return inputLottoNumbers();
        }
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");

        try {
            String input = SCANNER.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(INPUT_FORMAT_EXCEPTION_MESSAGE);
            return inputAmountOfCustom();
        }
    }

    private static void validateNullInputException(String input) {
        if (input == null) {
            throw new NullPointerException(INPUT_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateEmptyInputException(String input) {
        if (input.isEmpty()) {
            throw new NullPointerException(INPUT_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
