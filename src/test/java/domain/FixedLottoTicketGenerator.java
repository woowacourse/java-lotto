package domain;

import java.util.List;

public class FixedLottoTicketGenerator implements LottoTicketGenerator {
    public LottoTicket generateLottoTicket(int startInclusive, int endInclusive) {
        return new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
    }
}
