package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final static List<LottoTicket> LottoTickets = new ArrayList<>();

    public LottoTickets(LottoTicket lottoTicket) {
        LottoTickets.add(lottoTicket);
    }

    public static List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(LottoTickets);
    }



}
