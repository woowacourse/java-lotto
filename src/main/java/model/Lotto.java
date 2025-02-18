package model;

import dto.LottoDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final String LOTTO_FORM_ERROR_MESSAGE = "로또는 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";
    private static final int MAX_UNIQUE_NUMBERS = 12;

    private final Set<LottoNumber> lotto;

    public Lotto(Set<Integer> numbers) {
        validateLotto(numbers);
        this.lotto = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    public boolean contains(LottoNumber number) {
        return lotto.contains(number);
    }

    public LottoDto toDto() {
        return new LottoDto(
                lotto.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toSet())
        );
    }

    private void validateLotto(Set<Integer> numbers) {
        if(numbers.size() != 6) {
            throw new IllegalArgumentException(LOTTO_FORM_ERROR_MESSAGE);
        }
    }

    public int calculateDuplicateNumber(Lotto winningLotto) {
        Set<LottoNumber> union = new HashSet<>();
        union.addAll(lotto);
        union.addAll(winningLotto.lotto);
        return MAX_UNIQUE_NUMBERS - union.size();
    }
}
