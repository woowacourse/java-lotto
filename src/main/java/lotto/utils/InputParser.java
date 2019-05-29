package lotto.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.LottoNumber;
import lotto.exceptions.IllegalFormatException;

public class InputParser {
    private static final String INPUT_FORM_REGEX = "([^,]+)([, ][^,]+)*";
    private static final String ILLEGAL_INPUT_FORMAT = "입력 형식이 올바르지 않습니다.";
    private static final String DELIMITER = ",";

    public static List<LottoNumber> parseLotto(String input) {
        validLottoFormat(input);
        String[] tokens = split(input);
        Set<LottoNumber> inputNumbers = getInputNumbers(tokens);
        List<LottoNumber> lottoNumbers = validLottoNumbers(inputNumbers);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static void validLottoFormat(String input) {
        if (!input.matches(INPUT_FORM_REGEX)) {
            throw new IllegalFormatException(ILLEGAL_INPUT_FORMAT);
        }
    }

    private static Set<LottoNumber> getInputNumbers(String[] tokens) {
        Set<LottoNumber> temp = new HashSet<>();
        for (String token : tokens) {
            temp.add(LottoNumber.of(Integer.parseInt(token)));
        }
        return temp;
    }

    private static List<LottoNumber> validLottoNumbers(Set<LottoNumber> temp) {
        if (temp.size() != 6) {
            throw new IllegalFormatException("당첨 번호는 총 6개 입니다.");
        }
        return new ArrayList<>(temp);
    }

    private static String[] split(String input) {
        String[] result = StringUtils.deleteWhitespace(input).split(DELIMITER);
        if (result.length != 6) {
            throw new IllegalFormatException("당첨 번호는 6개 입니다.");
        }
        return result;
    }

    public static int parseBonus(String input) {
        validBonusFormat(input);
        return Integer.parseInt(input);
    }

    private static void validBonusFormat(String input) {
        if (!input.matches("[0-9]{1,2}")) {
            throw new IllegalArgumentException(ILLEGAL_INPUT_FORMAT);
        }
    }
}