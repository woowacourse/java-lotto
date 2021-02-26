package lottogame.domain.dto;

import lottogame.domain.lotto.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {
    private List<LottoNumber> numbers;

    public LottoDto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static LottoDto Of(List<Integer> numbers) {
        return new LottoDto(numbers.stream()
                .map(number -> LottoNumber.valueOf(number))
                .collect(Collectors.toList()));
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
