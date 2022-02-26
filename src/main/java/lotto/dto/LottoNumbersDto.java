package lotto.dto;

import lotto.domain.vo.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersDto {
    private final List<Integer> numbers;

    public LottoNumbersDto(final List<LottoNumber> numbers) {
        this.numbers = numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
