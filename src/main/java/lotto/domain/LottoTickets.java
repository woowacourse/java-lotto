package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoTickets {

    private static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";
    private static final String EMPTY_ERROR_MESSAGE = "로또는 한장 이상 구매해야 합니다.";

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, NULL_ERROR_MESSAGE);
        validateEmpty(lottoTickets);
        this.lottoTickets = lottoTickets;
    }

    public LottoTickets merge(LottoTickets otherLottoTickets) {
        this.addAll(otherLottoTickets);
        return this;
    }

    public List<LottoTicket> getTickets() {
        return this.lottoTickets;
    }

    public void addAll(LottoTickets lottoTicketsToAdd) {
        lottoTickets.addAll(lottoTicketsToAdd.getTickets());
    }

    public int size() {
        return lottoTickets.size();
    }

    private void validateEmpty(final List<LottoTicket> lottoTickets) {
        if (lottoTickets.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }
}
