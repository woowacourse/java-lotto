package lotto.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.exceptions.NumberCountException;
import lotto.domain.lotto.Number;
import lotto.exceptions.IllegalFormatException;

public class InputParser {
    private static final String INPUT_FORM_REGEX = "([^,]+)([, ][^,]+)*";
    private static final String DELIMITER = ",";
    private static final int LOTTO_SIZE = 6;

    public static List<Number> parseLotto(String input) {
        validLottoFormat(input);
        String[] tokens = split(input);
        List<Number> lottoNumbers = new ArrayList<>(getInputNumbers(tokens));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static void validLottoFormat(String input) {
        if (!input.matches(INPUT_FORM_REGEX)) {
            throw new IllegalFormatException("입력 형식이 올바르지 않습니다.");
        }
    }

    private static String[] split(String input) {
        return StringUtils.deleteWhitespace(input).split(DELIMITER);
    }

    private static Set<Number> getInputNumbers(String[] tokens) {
        Set<Number> result = new HashSet<>();
        for (String token : tokens) {
            validNumeric(token);
            result.add(Number.of(Integer.parseInt(token)));
        }
        validSize(result);
        return result;
    }

    private static void validSize(Set<Number> result) {
        if (result.size() != LOTTO_SIZE) {
            throw new NumberCountException("번호는 6개 입니다.");
        }
    }

    public static int parseNumber(String input) {
        validNumeric(input);
        return Integer.parseInt(input);
    }

    private static void validNumeric(String input) {
        if (!isNumeric(input)) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}