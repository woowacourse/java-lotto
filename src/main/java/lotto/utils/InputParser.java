package lotto.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.lotto.Number;
import lotto.domain.exceptions.IllegalFormatException;

public class InputParser {
    private static final String INPUT_FORM_REGEX = "([^,]+)([, ][^,]+)*";
    private static final String ILLEGAL_INPUT_FORMAT = "입력 형식이 올바르지 않습니다.";
    private static final String ILLEGAL_NUMBER_COUNT = "당첨 번호는 6개 입니다.";
    private static final String DELIMITER = ",";

    public static List<Number> parseLotto(String input) {
        validLottoFormat(input);
        String[] tokens = split(input);
        Set<Number> inputNumbers = getInputNumbers(tokens);
        List<Number> lottoNumbers = new ArrayList<>(inputNumbers);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static void validLottoFormat(String input) {
        if (!input.matches(INPUT_FORM_REGEX)) {
            throw new IllegalFormatException(ILLEGAL_INPUT_FORMAT);
        }
    }

    private static String[] split(String input) {
        return StringUtils.deleteWhitespace(input).split(DELIMITER);
    }

    private static Set<Number> getInputNumbers(String[] tokens) {
        Set<Number> result = new HashSet<>();
        for (String token : tokens) {
            result.add(Number.of(Integer.parseInt(token)));
        }
        validSize(result);
        return result;
    }

    private static void validSize(Set<Number> result) {
        if (result.size() != 6) {
            throw new IllegalFormatException(ILLEGAL_NUMBER_COUNT);
        }
    }
}