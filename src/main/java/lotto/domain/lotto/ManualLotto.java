package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.InvalidException;

public class ManualLotto {
    private static final String LOTTO_DELIMITER = ",";
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

    private List<Lotto> lottos = new ArrayList<>();

    public void add(String numbers) {
        numbers = removeBlank(numbers);
        checkInputManualLotto(numbers);
        lottos.add(new Lotto(createWinningLottoNumbers(numbers)));
    }

    private void checkInputManualLotto(String numbers) {
        checkNullAndBlank(numbers);
        checkDelimiterCount(numbers);
        String[] values = numbers.split(LOTTO_DELIMITER);
        checkNotInteger(values);
        checkIntegerRange(values);
        checkDuplicateNumber(values);
    }
    private void checkNullAndBlank(final String numbers) {
        if (numbers == null || numbers.isBlank()) {
            throw new IllegalArgumentException(InvalidException.ERROR_NULL_BLANK);
        }
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    private static void checkDelimiterCount(final String numbers) {
        if (numbers.chars()
                .filter(c -> c == LOTTO_DELIMITER.charAt(0))
                .count() != LOTTO_SIZE - 1) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private static void checkNotInteger(final String[] values) {
        try {
            Arrays.stream(values)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(InvalidException.ERROR_NOT_INTEGER);
        }
    }

    private static void checkIntegerRange(final String[] values) {
        if (!Arrays.stream(values)
                .map(Integer::parseInt)
                .allMatch(number -> LOTTO_MIN_RANGE <= number
                        && number <= 45)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateNumber(final String[] values) {
        if (LOTTO_SIZE != Set.copyOf(Arrays.asList(values)).size()) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private List<Integer> createWinningLottoNumbers(final String numbers) {
        return Arrays.stream(numbers.split(LOTTO_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
