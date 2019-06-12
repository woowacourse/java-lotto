package lotto.dto;

import java.util.List;

public class LottoDto {

    private List<Integer> lottoNumbers;

    public LottoDto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public void setLottoNumbers(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
}
