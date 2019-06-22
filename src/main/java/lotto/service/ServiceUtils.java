package lotto.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ServiceUtils {
    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("정수를 입력해 주세요.");
        }
    }

    public static List<Integer> parseInts(String input) {
        try {
            Pattern.compile("\\d(\\d)?(,\\d(\\d)?)*").matcher(StringUtils.deleteWhitespace(input));
            return Arrays.asList(input
                    .split(","))
                    .stream()
                    .map(n -> parseInt(n))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("정수를 콤마를 이용하여 구분해 입력해 주세요. (e.g., \"1, 2, 3, 4, 5, 6\")");
        }
    }
}
