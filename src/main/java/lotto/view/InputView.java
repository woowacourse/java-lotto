package lotto.view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_LOTTO_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String TYPE_ERROR = "숫자만 입력 가능합니다.";
    private static final String BLANK = " ";
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputAmount() {
        System.out.println(INPUT_LOTTO_AMOUNT);
        return convertToInt(scanner.nextLine());
    }

    public static List<Integer> inputLottoNumbers() {
        String[] splitInput = split(reduceBlank(scanner.nextLine()));
        return convertToIntegerList(splitInput);
    }

    private static String reduceBlank(String input) {
        return input.replaceAll(BLANK, EMPTY_STRING);
    }

    private static String[] split(String input) {
        return input.split(DELIMITER);
    }

    private static List<Integer> convertToIntegerList(String[] array) {
        return Arrays.stream(array)
                .map(InputView::convertToInt)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public static int inputBonusBall() {
        System.out.println(INPUT_BONUS_NUMBER);
        String input = scanner.nextLine();
        return convertToInt(input);
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(TYPE_ERROR);
        }
    }

}
