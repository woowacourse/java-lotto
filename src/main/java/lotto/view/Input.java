package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {
    private final static Scanner SCANNER;
    private final static String INPUT_DELIMITER;
    private final static String ERROR_INPUT_NUMBER;
    private final static String EMPTY;

    static {
        SCANNER = new Scanner(System.in);
        ERROR_INPUT_NUMBER = "올바른 숫자가 입력되지 않았습니다. 다시 입력해 주세요.";
        INPUT_DELIMITER = ",\\s*"; // 구분자는 쉼표
        EMPTY = "";
    }

    private static String inputWithMessage(String message) {
        if (!message.equals(EMPTY)) {
            System.out.println(message);
        }
        return SCANNER.nextLine().strip();
    }

    private static String[] getInput(String message) {
        String input = inputWithMessage(message);
        return input.split(INPUT_DELIMITER);
    }

    /**
     * 입력된 문자열을 자연수로 형변환할 수 있을지 테스트
     */
    private static boolean isOnlyNumber(String string) {
        if (string.length() == 0) {
            return false; // 빈 문자열은 취급하지 않는다.
        }
        return string.chars() // String -> IntStream
                .map(i -> (i - '0')) // 형변환 발생
                .noneMatch(i -> (i < 0 || i > 9)); // 0부터 9 사이의 숫자인가
    }

    public static int getSingleInt(String message) {
        String temp = getInput(message)[0];
        boolean check = isOnlyNumber(temp);
        while (!check) { // 입력에 문제가 있으면 계속 재입력 요구
            temp = getInput(ERROR_INPUT_NUMBER)[0];
            check = isOnlyNumber(temp);
        }
        return Integer.parseInt(temp);
    }

    public static List<Integer> tryGetNumbers() {
        Stream<String> temp = Arrays.stream(getInput(EMPTY));
        try {
            return temp.map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_INPUT_NUMBER);
        }
    }
}
