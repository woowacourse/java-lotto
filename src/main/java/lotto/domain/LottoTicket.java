package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.NumberOutOfRangeException;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private List<LottoBall> lottoTicket;

    public LottoTicket(List<LottoBall> lottoTicket) {
        validateLottoTicket(lottoTicket);
        Collections.sort(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void validateLottoTicket(List<LottoBall> lottoTicket){
        if (lottoTicket.isEmpty()) {
            throw new LottoTicketEmptyException("로또 티켓에 로또볼이 비었습니다. 다시 드리겠습니다.");
        }
        if(lottoTicket.size() > 6){
            throw new NumberOutOfRangeException("7개의 숫자가 들어갔습니다.");
        }
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
