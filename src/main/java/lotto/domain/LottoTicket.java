package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.LottoTicketOutOfRange;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;

    private List<LottoBall> lottoTicket;

    public LottoTicket(List<LottoBall> lottoTicket) {
        if (lottoTicket.isEmpty()) {
            throw new LottoTicketEmptyException("로또 티켓에 로또볼이 비었습니다. 다시 드리겠습니다.");
        }
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new LottoTicketOutOfRange("로또 티켓이 7장이상 발급되었습니다.");
        }
        Collections.sort(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
