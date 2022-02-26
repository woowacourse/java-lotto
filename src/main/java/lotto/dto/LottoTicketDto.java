package lotto.dto;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.vo.LottoNumber;

public class LottoTicketDto {

    private final List<LottoNumber> lottoNumbers;

    public LottoTicketDto(LottoTicket lottoTicket) {
        this.lottoNumbers = lottoTicket.getLottoNumbers();
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
