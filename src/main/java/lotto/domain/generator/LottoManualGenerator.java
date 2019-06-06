package lotto.domain.generator;

import lotto.domain.LottoContainer;
import lotto.domain.LottoNumber;

import java.util.*;

public class LottoManualGenerator {
    private static final String DUPLICATE_MESSAGE = "중복된 숫자가 입력되었습니다.";
    private static final String INVALID_LENGTH_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static List<LottoNumber> generate(String input) {
        List<LottoNumber> lottoNumbers = LottoContainer.createLottoNumbers();
        List<Integer> parsedInput = splitInput(input);
        List<LottoNumber> retNumbers = new ArrayList<>();
        for (Integer index : parsedInput) {
            retNumbers.add(lottoNumbers.get(index - 1));
        }
        return retNumbers;
    }

    private static List<Integer> splitInput(String input) {
        List<String> splittedInput = Arrays.asList(input.split(","));
        List<Integer> parsedInput = new ArrayList<>();
        for (String s : splittedInput) {
            parsedInput.add(Integer.parseInt(s));
        }
        checkLottoLength(parsedInput);
        checkDuplicate(parsedInput);
        return parsedInput;
    }

    private static void checkDuplicate(List<Integer> parsedInput) {
        Set<Integer> unduplicated = new HashSet<>(parsedInput);
        if (unduplicated.size() != parsedInput.size()) {
            throw new IllegalArgumentException(DUPLICATE_MESSAGE);
        }
    }

    private static void checkLottoLength(List<Integer> parsedInput) {
        if (parsedInput.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
        }
    }
}
