package dto;

import domain.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {
    private List<Integer> lottoNumber;

    public LottoDto(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoDto from(List<LottoNumber> lottoNumbers){
        List<Integer> numbers = lottoNumbers.stream()
                .map(LottoNumber::number)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(numbers);
    }
}
