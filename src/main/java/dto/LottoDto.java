package dto;

import domain.Lotto.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {

    private List<Integer> lottoNumber;

    public LottoDto(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoDto from(List<LottoNumber> lottoNumbers) {
        List<Integer> numbers = lottoNumbers.stream()
                .map(LottoNumber::number)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(numbers);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
