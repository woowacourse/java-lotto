package service.dto;

import java.util.List;

public class LottoDto {
    private List<Integer> lottoNumbers;

    public LottoDto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
