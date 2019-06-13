package lotto.domain.util;

import lotto.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomStringUtils {
    public static void checkIsBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new InvalidInputException("아무것도 입력하지 않으셨습니다.");
        }
    }

    public static List<Integer> parseInts(String input) {
        List<Integer> numbers = new ArrayList<>();

        for (String s : input.split(",")) {
            numbers.add(parseInt(s));
        }
        return numbers;
    }

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자로 입력해주세요.");
        }
    }
}
