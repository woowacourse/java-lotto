package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final static List<LottoTicket> LOTTO_TICKETS = new ArrayList<>();

    public LottoTickets(LottoTicket lottoTicket) {
        LOTTO_TICKETS.add(lottoTicket);
    }

    public static List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(LOTTO_TICKETS);
    }

}
