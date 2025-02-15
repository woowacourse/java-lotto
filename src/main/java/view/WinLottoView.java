package view;

import constant.ErrorMessage;
import constant.OutputMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinLottoView {
    private static final String DELIMITER = ",";

    public void printWinNumberGuide() {
        System.out.println(OutputMessage.WIN_NUMBERS);
    }

    public List<Integer> readWinNumbers() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> splitInput = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
        return splitInput.stream().mapToInt(this::validatePositiveNumber).boxed().toList();
    }

    public void printBonusNumberGuide() {
        System.out.println(OutputMessage.BONUS_NUMBER);
    }

    public Integer readBonusNumber() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return validatePositiveNumber(input);
    }

    private Integer validatePositiveNumber(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
        }
        return Integer.parseInt(input);
    }
}
