package domain;

import java.util.Set;

public class Ticket {
    private final Set<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbers = lottoNumbersGenerator.generate();
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getSameLottoNumberCount(Set<LottoNumber> lottoNumbers) {
        return (int)lottoNumbers.stream()
            .filter(this.lottoNumbers::contains)
            .count();
    }
}
