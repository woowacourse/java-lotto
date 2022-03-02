package lotto.domain.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

public class LottoFactory {

    public static final String TEXT_DELIMITER = ", ";

    private static final int SUB_LIST_FROM_INDEX = 0;
    private static final List<Integer> LOTTO_NUMBERS = new ArrayList<>();

    static {
        for (int number = Number.MIN_VALUE; number <= Number.MAX_VALUE; number++) {
            LOTTO_NUMBERS.add(number);
        }
    }

    public static Lotto auto() {
        Collections.shuffle(LOTTO_NUMBERS);
        return new Lotto(LOTTO_NUMBERS.subList(SUB_LIST_FROM_INDEX, Lotto.SIZE));
    }

    public static Lotto valueOf(String text) {
        String[] splitText = text.split(TEXT_DELIMITER);
        return new Lotto(toNumberList(splitText));
    }

    private static List<Integer> toNumberList(String[] splitText) {
        try {
            return Arrays.stream(splitText)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
