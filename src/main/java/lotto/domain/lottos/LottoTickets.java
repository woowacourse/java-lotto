package lotto.domain.lottos;

import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoWinner;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoTickets {

    private static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";
    private static final String EMPTY_ERROR_MESSAGE = "로또는 한장 이상 구매해야 합니다.";
    private static final int INCREMENT_COUNT = 1;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, NULL_ERROR_MESSAGE);
        validateEmptyTickets(lottoTickets);
        this.lottoTickets = lottoTickets;
    }

    public void putLottoResult(Map<LottoRank, Integer> lottoResult, LottoWinner lottoWinner) {
        this.lottoTickets.forEach(lottoTicket -> {
            LottoBoughtTicket lottoBoughtTicket = (LottoBoughtTicket) lottoTicket;
            LottoRank rank = lottoBoughtTicket.getRank(lottoWinner);
            lottoResult.put(rank, lottoResult.getOrDefault(rank, 0) + INCREMENT_COUNT);
        });
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(this.lottoTickets);
    }

    private void validateEmptyTickets(final List<LottoTicket> lottoTickets) {
        if (lottoTickets.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }
}
