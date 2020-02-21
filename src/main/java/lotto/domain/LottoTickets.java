package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private static final List<LottoTicket> LOTTO_TICKETS = new ArrayList<>();

    public static void insertLottoTicket(LottoTicket lottoTicket) {
        LOTTO_TICKETS.add(lottoTicket);
    }

    public static List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(LOTTO_TICKETS);
    }
}
