package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.LottoTicketOutOfRange;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int LOTTO_TICKET_SIZE = 6;
    private static final String LOTTO_TICKET_EMPTY_EXCEPTION = "로또 티켓에 로또볼이 비었습니다. 다시 드리겠습니다.";
    private static final String LOTTO_TICKET_OUT_OF_RANGE = "로또 티켓이 7장이상 발급되었습니다.";

    private final List<LottoBall> lottoTicket;

    public LottoTicket(List<LottoBall> lottoTicket) {
        if (lottoTicket.isEmpty()) {
            throw new LottoTicketEmptyException(LOTTO_TICKET_EMPTY_EXCEPTION);
        }
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new LottoTicketOutOfRange(LOTTO_TICKET_OUT_OF_RANGE);
        }
        this.lottoTicket = lottoTicket;
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
