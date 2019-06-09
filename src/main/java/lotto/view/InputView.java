package lotto.view;

import lotto.exception.ExceptionMessage;
import lotto.exception.IllegalAmountOfNumberException;
import lotto.exception.UnexpectedInputRangeException;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int LOTTO_NUMBER_AMOUNT = 6;

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        try {
            String input = SCANNER.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessage.INPUT_FORMAT_EXCEPTION_MESSAGE);
            return inputMoney();
        }
    }

    public static int inputAmountOfCustom(int maxCount) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        try {
            int input = Integer.parseInt(SCANNER.nextLine());
            validateAmountOfCustomsException(input, maxCount);
            return input;
        } catch (Exception e) {
            System.out.println(ExceptionMessage.INPUT_FORMAT_EXCEPTION_MESSAGE);
            return inputAmountOfCustom(maxCount);
        }
    }

    public static void printCustomLottoNumbersMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printWinningLottoNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");

        try {
            String input = SCANNER.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessage.INPUT_FORMAT_EXCEPTION_MESSAGE);
            return inputBonusBall();
        }
    }

    public static String inputLottoNumbers() {
        try {
            String input = SCANNER.nextLine();

            validateNullInputException(input);
            validateEmptyInputException(input);
            validateAmountOfNumber(input);

            return input.replaceAll(" ", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputLottoNumbers();
        }
    }

    private static void validateAmountOfNumber(String input) {
        if (input.split(",").length != LOTTO_NUMBER_AMOUNT) {
            throw new IllegalAmountOfNumberException(ExceptionMessage.ILLEGAL_AMOUNT_OF_LOTTO_NUMBER_EXCEPTION);
        }
    }

    private static void validateAmountOfCustomsException(int input, int maxCount) {
        if (input < 1 || input > maxCount) {
            throw new UnexpectedInputRangeException(ExceptionMessage.INPUT_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNullInputException(String input) {
        if (input == null) {
            throw new NullPointerException(ExceptionMessage.INPUT_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateEmptyInputException(String input) {
        if (input.isEmpty()) {
            throw new NullPointerException(ExceptionMessage.INPUT_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
