package lotto.dto;

import lotto.domain.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {
    private final List<LottoNumber> lottoNumbers;

    public LottoDto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.toList())
                .toString();
    }
}
