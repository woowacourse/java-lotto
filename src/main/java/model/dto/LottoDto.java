package model.dto;

import java.util.List;
import model.Lotto;
import model.LottoNumber;

public class LottoDto {
    private final List<LottoNumber> lottoNumbers;

    public LottoDto(Lotto lotto) {
        this.lottoNumbers = List.copyOf(lotto.getLottoNumbers());
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
