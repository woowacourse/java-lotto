package lotto.util;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    private static final String COMMA = ",";
    private static final String BLANK = "";
    private static final String SPACE = " ";
    private static final String NUMBER_INPUT_ERROR_MESSAGE = "숫자 외의 다른 문자가 입력되었습니다.";

    public static List<Integer> splitInput(String input) {
        input = removeBlank(input);
        String[] splitedInput = input.split(COMMA);
        return toResultInput(splitedInput);
    }

    private static List<Integer> toResultInput(String[] splitedInput) {
        List<Integer> result = new ArrayList<>();
        try {
            for (String input : splitedInput) {
                result.add(Integer.parseInt(input));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_INPUT_ERROR_MESSAGE);
        }
        return result;
    }

    private static String removeBlank(String input) {
        return input.replace(SPACE, BLANK);
    }
}
