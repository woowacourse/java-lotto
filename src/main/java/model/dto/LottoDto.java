package model.dto;

import java.util.List;
import model.LottoNumber;

public class LottoDto {
    private final List<LottoNumber> lottoNumbers;

    public LottoDto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
