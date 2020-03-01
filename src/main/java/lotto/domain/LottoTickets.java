package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public void insertLottoTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

}
