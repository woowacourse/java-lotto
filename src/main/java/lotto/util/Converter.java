package lotto.util;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<Integer> convertToIntegerList(String input) {
        String[] split = input.split(",");

        List<Integer> numbers = new ArrayList<>();
        try {
            for (String s : split) {
                numbers.add(Integer.parseInt(s.trim()));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨번호는 숫자만 입력해야합니다.", e);
        }

        return numbers;
    }

}
