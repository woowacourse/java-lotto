package domain;

import java.util.List;
import java.util.Set;

public class Ticket {
    private static final int TICKET_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Ticket(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
        }
    }
}
