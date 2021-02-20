package domain;

import java.util.Collections;
import java.util.List;

/**
 * LottoTickets.java
 * 로또 티켓에 대한 일급 컬렉션
 *
 * @author Kimun Kim, github.com/tributetothemoon스
 * @author Daeun Lee, github.com/da-nyee
 */
public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public int size() {
        return lottoTickets.size();
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
