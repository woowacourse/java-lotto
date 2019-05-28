package lotto.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.domain.LottoNumber;
import lotto.exceptions.IllegalInputFormatException;

public class InputParser {
    private static final String INPUT_FORM_REGEX = "([^,]+)([, ][^,]+)*";
    private static final String ILLEGAL_INPUT_FORMAT = "입력 형식이 올바르지 않습니다.";
    private static final String DELIMITER = ",";

    public static List<LottoNumber> parse(String input) {
        valid(input);
        String[] tokens = split(input);
        List<LottoNumber> result = new ArrayList<>();
        for (String token : tokens) {
            result.add(LottoNumber.of(Integer.parseInt(token)));
        }
        Collections.sort(result);
        return result;
    }

    private static String[] split(String input) {
        String[] result = StringUtils.deleteWhitespace(input).split(DELIMITER);
        if (result.length != 6) {
            throw new IllegalInputFormatException("당첨 번호는 6개 입니다.");
        }
        return result;
    }

    private static void valid(String input) {
        if (!input.matches(INPUT_FORM_REGEX)) {
            throw new IllegalInputFormatException(ILLEGAL_INPUT_FORMAT);
        }
    }
}
