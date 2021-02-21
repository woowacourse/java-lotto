package lotto.domain.ticket;

import java.util.List;

public class LottoTicketFactory {
    public LottoTicket createLottoTicket(List<Integer> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }
}
