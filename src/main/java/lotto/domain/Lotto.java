package lotto.domain;

import static lotto.common.constant.Constant.*;
import static lotto.common.exception.ErrorMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);

        this.numbers = new ArrayList<>(numbers);
    }

    private static void validate(List<LottoNumber> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateNull(List<LottoNumber> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> set = new HashSet<>();
        for (LottoNumber number : numbers) {

            if (!set.add(number)) {
                throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_DUPLICATE);
            }
        }
    }

    public MatchResult countMatchingNumbers(Lotto matchLotto, LottoNumber bonus) {

        int count = (int) numbers.stream()
                .filter(matchLotto::contains)
                .count();

        return new MatchResult(count, contains(bonus));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

}
