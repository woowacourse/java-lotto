package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final static List<LottoTicket> LOTTO_TICKETS = new ArrayList<>();

    public void addLottoTicket(LottoTicket lottoTicket) {
        LOTTO_TICKETS.add(lottoTicket);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(LOTTO_TICKETS);
    }
}