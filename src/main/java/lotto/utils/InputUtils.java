package lotto.utils;

import lotto.view.input.InputCheck;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputUtils {
    public static List<Integer> parseIntegerList(String text) {
        List<String> list = Arrays.asList(StringUtils.split(text, ","));
        List<Integer> numbers = new ArrayList<>();
        for (String value : list) {
            numbers.add(InputCheck.parseInteger(value));
        }
        return numbers;
    }
}
