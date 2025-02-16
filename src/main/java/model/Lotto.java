package model;

import dto.LottoDto;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private static final String LOTTO_FORM_ERROR_MESSAGE = "로또는 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";

    private final Set<Integer> lotto;

    public Lotto(Set<Integer> numbers) {
        validateLotto(numbers);
        this.lotto = numbers;
    }

    public boolean contains(int number) {
        return lotto.contains(number);
    }

    public LottoDto toDto() {
        return new LottoDto(lotto);
    }

    private void validateLotto(Set<Integer> numbers) {
        if(numbers.size() != 6) {
            throw new IllegalArgumentException(LOTTO_FORM_ERROR_MESSAGE);
        }
        numbers.forEach(number -> {
            if (!isValidateNumberRange(number)) {
                throw new IllegalArgumentException(LOTTO_FORM_ERROR_MESSAGE);
            }
        });
    }

    private boolean isValidateNumberRange(int number) {
        return number >= 1 && number <= 45;
    }
}
