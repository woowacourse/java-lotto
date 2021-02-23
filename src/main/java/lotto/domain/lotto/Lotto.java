package lotto.domain.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    public static final String LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "잘못된 개수의 입력입니다.";
    public static final String DUPLICATE_NUMBER_ERROR_MESSAGE = "중복된 숫자 입력입니다.";
    private final List<LottoNumber> numbers;

    public static Lotto from(final List<Integer> numbers) {
        return new Lotto(numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList()));
    }

    public Lotto(final List<LottoNumber> numbers) {
        validateNumberCount(numbers);
        validateDistinct(numbers);
        this.numbers = numbers;
    }

    private void validateNumberCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    private void validateDistinct(final List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR_MESSAGE);
        }
    }

    public int countCommonValue(final Lotto lotto) {
        int totalCount = numbers.size() + lotto.numbers.size();
        Set<LottoNumber> set = new HashSet<>(numbers);
        set.addAll(lotto.numbers);
        return totalCount - set.size();
    }

    public boolean containNumber(final LottoNumber input) {
        return numbers.contains(input);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers.stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList()));
    }
}
