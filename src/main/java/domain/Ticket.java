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

    public Rank getRank(Set<LottoNumber> winNumbers, LottoNumber bonusNumber) {
        int count = (int)winNumbers.stream()
            .filter(this.lottoNumbers::contains)
            .count();

        int bonus = 0;
        if (count == 5) {
            if (this.lottoNumbers.contains(bonusNumber)) {
                bonus++;
            }
        }
        return Rank.value(count, bonus);
    }

    public boolean contains(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
