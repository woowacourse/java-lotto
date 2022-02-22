package domain;

import java.util.List;

public class Ticket {
    private final List<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbers = lottoNumbersGenerator.generate();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
