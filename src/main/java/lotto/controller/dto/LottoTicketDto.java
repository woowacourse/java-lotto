package lotto.controller.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;

public class LottoTicketDto {

    private final List<Integer> lottoNumbers;

    public LottoTicketDto(LottoTicket lottoTicket) {
        this.lottoNumbers = new ArrayList<>(lottoTicket.getLottoNumbers());
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
