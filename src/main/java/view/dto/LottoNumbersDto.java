package view.dto;

import domain.lotto.LottoNumber;

import java.util.List;

public class LottoNumbersDto {
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbersDto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
