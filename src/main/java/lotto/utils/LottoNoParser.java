package lotto.utils;

import lotto.domain.InvalidFormatException;
import lotto.domain.lotto.LottoNo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNoParser {
    private static final String NOT_INTEGER_ERROR_MSG = "정수가 아닌 문자가 있습니다. 다시 입력해주세요.";
    private static final String INVALID_FORMAT_ERROR_MSG = "잘못된 입력 형식입니다.";
    private static final String SPLITTER = ",";

    public static List<LottoNo> parseToLottoNos(String rawInput) {
        try {
            validateInputValidFormat(rawInput);
            return Arrays.stream(rawInput.replaceAll(" ", "").split(SPLITTER))
                    .map(Integer::parseInt)
                    .map(LottoNo::of)
                    .collect(Collectors.toList());
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(NOT_INTEGER_ERROR_MSG);
        }
    }

    private static void validateInputValidFormat(String rawInput) {
        if (rawInput.startsWith(SPLITTER) || rawInput.endsWith(SPLITTER) || rawInput.contains(SPLITTER + SPLITTER)) {
            throw new InvalidFormatException(INVALID_FORMAT_ERROR_MSG);
        }
    }
}