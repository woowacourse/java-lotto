package domain.lotto;

import java.util.Set;

public class LottoTicket {

    private static final int LOTTO_TICKET_SIZE = 6;
    private Set<LottoNumber> lottoTicket;

    public LottoTicket(Set<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException();
        }
        this.lottoTicket = lottoTicket;
    }
}
