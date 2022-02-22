package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final String DUPLICATED_LOTTO_NUMBERS_EXCEPTION_MESSAGE = "같은 줄 로또 번호 간에 중복이 존재합니다.";

    private final List<LottoNumber> values;

    public LottoNumbers(final List<String> numberValues) {
        validateDuplication(numberValues);
        values = sortAscendingLottoNumbers(numberValues);
    }

    private List<LottoNumber> sortAscendingLottoNumbers(final List<String> numberValues) {
        return numberValues.stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplication(final List<String> numberValues) {
        final Set<String> checkedNumberValues = new HashSet<>();
        for (String numberValue : numberValues) {
            checkDuplication(checkedNumberValues, numberValue);
            checkedNumberValues.add(numberValue);
        }
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
