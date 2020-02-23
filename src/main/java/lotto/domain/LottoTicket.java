package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.NumberOutOfRangeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;
    public static final String NUMBER_OUT_OF_RANGE_ERROR_MESSAGE = String.format("%d개 이상의 숫자가 들어갔습니다.",
            LOTTO_TICKET_SIZE);
    public static final String LOTTO_TICKET_EMPTY_ERROR_MESSAGE = "로또 티켓에 로또볼이 비었습니다. 다시 드리겠습니다.";

    private List<LottoBall> lottoTicket;

    public LottoTicket(Set<LottoBall> lottoTicket) {
        validateLottoTicket(lottoTicket);
        this.lottoTicket = new ArrayList<>(lottoTicket);
        Collections.sort(this.lottoTicket);
    }

    private void validateLottoTicket(Set<LottoBall> lottoTicket) {
        if (lottoTicket.isEmpty()) {
            throw new LottoTicketEmptyException(LOTTO_TICKET_EMPTY_ERROR_MESSAGE);
        }
        if (lottoTicket.size() > LOTTO_TICKET_SIZE) {
            throw new NumberOutOfRangeException(NUMBER_OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}