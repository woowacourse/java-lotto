package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private List<LottoBall> lottoTicket;

    public LottoTicket(List<LottoBall> lottoTicket) {
        if (lottoTicket.isEmpty()) {
            throw new LottoTicketEmptyException("로또 티켓에 로또볼이 비었습니다. 다시 드리겠습니다.");
        }
        Collections.sort(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
