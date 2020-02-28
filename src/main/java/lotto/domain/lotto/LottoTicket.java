package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class LottoTicket {

    private static final int LOTTO_TICKET_SIZE = 6;

    private Set<LottoNumber> lottoTicket;

    LottoTicket(Set<LottoNumber> lottoTicket) {
        validateSize(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void validateSize(Set<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new LottoTicketException();
        }
    }

    int countMatches(LottoTicket targetTicket) {
        return (int) lottoTicket.stream()
                .filter(targetTicket::contains)
                .count();
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }

    @Override
    public String toString() {
        List<LottoNumber> lottoTicketList = new ArrayList<>(lottoTicket);
        Collections.sort(lottoTicketList);
        return lottoTicketList.toString();
    }
}
