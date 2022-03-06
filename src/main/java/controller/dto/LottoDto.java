package controller.dto;

import domain.Lotto;
import domain.LottoNumber;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoDto {
    private final Set<Integer> numbers;

    public LottoDto(Set<Integer> numbers) {
        this.numbers = new LinkedHashSet<>(numbers);
    }

    public static LottoDto from(Lotto lotto) {
        Set<LottoNumber> lottoNumbers = lotto.getNumbers();
        Set<Integer> collect = lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return new LottoDto(collect);
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }
}
