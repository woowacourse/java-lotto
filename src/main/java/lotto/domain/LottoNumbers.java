package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {
    private static final String DUPLICATED_LOTTO_NUMBERS_EXCEPTION_MESSAGE = "같은 줄 로또 번호 간에 중복이 존재합니다.";

    public LottoNumbers(final List<String> numberValues) {
        validateDuplication(numberValues);
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
}
