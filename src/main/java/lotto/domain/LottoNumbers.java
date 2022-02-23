package lotto.domain;

import lotto.domain.vo.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final String DUPLICATED_LOTTO_NUMBERS_EXCEPTION_MESSAGE = "같은 줄 로또 번호 간에 중복이 존재합니다.";
    private static final String LOTTO_NUMBERS_COUNT_EXCEPTION_MESSAGE = "로또 숫자는 한 줄에 6개여야 합니다.";
    private static final int VALID_LOTTO_NUMBERS_COUNT = 6;

    private final List<LottoNumber> values;

    public LottoNumbers(final List<String> numberValues) {
        validateDuplication(numberValues);
        validateCountOfNumbers(numberValues);
        values = sortAscendingLottoNumbers(numberValues);
    }

    public LottoNumbers(final Set<LottoNumber> lottoValues) {
        values = lottoValues.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplication(final List<String> numberValues) {
        final Set<String> checkedNumberValues = new HashSet<>();
        for (String numberValue : numberValues) {
            checkDuplication(checkedNumberValues, numberValue);
            checkedNumberValues.add(numberValue);
        }
    }

    private void validateCountOfNumbers(final List<String> numberValues) {
        if (numberValues.size() != VALID_LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private List<LottoNumber> sortAscendingLottoNumbers(final List<String> numberValues) {
        return numberValues.stream()
                .sorted()
                .map(LottoNumber::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private void checkDuplication(Set<String> checkedNumberValues, String numberValue) {
        if (checkedNumberValues.contains(numberValue)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBERS_EXCEPTION_MESSAGE);
        }
    }

    public List<LottoNumber> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
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
