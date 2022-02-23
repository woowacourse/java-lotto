package lotto.dto;

import lotto.domain.vo.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersDto {
    private final List<Integer> lottoNumbers;

    public LottoNumbersDto(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
