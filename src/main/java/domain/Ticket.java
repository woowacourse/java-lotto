package domain;

import java.util.List;
import java.util.Set;

public class Ticket extends AbstractNumbersContainer {

    private final Set<LottoNumber> lottoNumbers;

    public Ticket(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }
}
