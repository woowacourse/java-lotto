package lotto.utils;

import lotto.CharacterParseIntegerException;
import lotto.domain.LottoNo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNoParser {
    private static final String SPLITTER = ",";

    public static List<LottoNo> parseToLottoNos(String rawInput) throws CharacterParseIntegerException {
        try {
            return Arrays.stream(rawInput.replaceAll(" ", "").split(SPLITTER))
                    .map(Integer::parseInt).map(LottoNo::new).collect(Collectors.toList());
        } catch (NumberFormatException nfe) {
            throw new CharacterParseIntegerException("문자를 입력하셨습니다.");
        }
    }
}
