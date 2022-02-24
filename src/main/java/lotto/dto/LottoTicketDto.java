package lotto.dto;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;

public class LottoTicketDto {

    private final List<Integer> lottoNumbers;

    public LottoTicketDto(LottoTicket lottoTicket) {
        this.lottoNumbers = lottoTicket.getLottoNumbers();
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
