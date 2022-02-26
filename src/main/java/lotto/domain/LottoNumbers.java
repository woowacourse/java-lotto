package lotto.domain;

import lotto.domain.vo.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final String DUPLICATED_LOTTO_NUMBERS_EXCEPTION_MESSAGE = "같은 줄 로또 번호 간에 중복이 존재합니다.";
    private static final String LOTTO_NUMBERS_COUNT_EXCEPTION_MESSAGE = "로또 숫자는 한 줄에 6개여야 합니다.";
    private static final int VALID_LOTTO_NUMBERS_COUNT = 6;

    private final List<LottoNumber> values;

    public LottoNumbers(final Set<LottoNumber> numbers) {
        values = numbers.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    public LottoNumbers(final List<String> numbers) {
        validateDuplication(numbers);
        validateCountOfNumbers(numbers);
        final List<LottoNumber> mappedLottoNumbers = mapToLottoNumbers(numbers);
        this.values = sortAscendingLottoNumbers(mappedLottoNumbers);
    }

    private void validateDuplication(final List<String> numbers) {
        final Set<String> checkedNumberValues = new HashSet<>();
        for (String number : numbers) {
            checkDuplication(checkedNumberValues, number);
            checkedNumberValues.add(number);
        }
    }

    private void checkDuplication(final Set<String> checkedNumbers, final String checkingNumber) {
        if (checkedNumbers.contains(checkingNumber)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBERS_EXCEPTION_MESSAGE);
        }
    }

    private void validateCountOfNumbers(final List<String> numbers) {
        if (numbers.size() != VALID_LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private List<LottoNumber> mapToLottoNumbers(final List<String> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<LottoNumber> sortAscendingLottoNumbers(final List<LottoNumber> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    public int getMatchCount(final LottoNumbers others) {
        return (int) values.stream()
                .filter(targetNumber -> targetNumber.hasSameNumberWith(others.values))
                .count();
    }

    public boolean hasSameNumberWith(final LottoNumber another) {
        return another.hasSameNumberWith(values);
    }

    public List<LottoNumber> getValues() {
        return values;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
