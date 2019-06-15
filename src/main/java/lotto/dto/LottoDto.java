package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;

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

    public Lotto toEntity() {
        List<LottoNumber> wrappedLottoNumbers = generateLottoNumbers(lottoNumbers);
        return new Lotto(wrappedLottoNumbers);
    }
}
