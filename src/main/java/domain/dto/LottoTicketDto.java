package domain.dto;

import java.util.Collections;
import java.util.Set;

public class LottoTicketDto {
    private final Set<Integer> lottoNumbers;

    public LottoTicketDto(Set<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Set<Integer> getLottoNumbers() {
        return Set.copyOf(lottoNumbers);
    }
}
