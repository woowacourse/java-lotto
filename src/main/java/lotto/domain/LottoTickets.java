package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoTickets {

    private static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";
    private static final String EMPTY_ERROR_MESSAGE = "로또는 한장 이상 구매해야 합니다.";

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, NULL_ERROR_MESSAGE);
        validateEmptyTickets(lottoTickets);
        this.lottoTickets = lottoTickets;
    }

    public List<LottoTicket> getLottoTickets() {
        return this.lottoTickets;
    }

    public void addAll(LottoTickets lottoTicketsToAdd) {
        lottoTickets.addAll(lottoTicketsToAdd.getLottoTickets());
    }

    public int size() {
        return lottoTickets.size();
    }

    private void validateEmptyTickets(final List<LottoTicket> lottoTickets) {
        if (lottoTickets.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }
}
