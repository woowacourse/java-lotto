package domain;

import java.util.Set;

public class Ticket {
    private final Set<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbers = lottoNumbersGenerator.generate();
    }

    public Ticket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean contains(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getSameNumberCount(Ticket ticket) {
        return (int)ticket.getLottoNumbers().stream()
            .filter(lottoNumbers::contains)
            .count();
    }
}
