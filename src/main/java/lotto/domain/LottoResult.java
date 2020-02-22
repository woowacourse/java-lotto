package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    public static final int BONUS_COUNT = 5;
    private Map<Rank, Integer> lottoResult;

    private LottoResult() {
        this.lottoResult = new HashMap<>();
        Arrays.stream(Rank.values())
            .forEach(rank -> lottoResult.put(rank, 0));
    }

    public static LottoResult create() {
        return new LottoResult();
    }

    public Map<Rank, Integer> match(LottoTickets tickets, LottoTicket winningTicket, LottoNumber bonus) {
        for (LottoTicket ticket : tickets) {
            int matchCount = winningTicket.compare(ticket);
            Rank rankFound = Rank.find(matchCount);
            lottoResult.put(rankFound, lottoResult.get(rankFound) + 1);
            handleBonus(bonus, ticket, matchCount);
        }
        return Collections.unmodifiableMap(lottoResult);
    }

    private void handleBonus(LottoNumber bonus, LottoTicket ticket, int matchCount) {
        if (isBonus(bonus, ticket, matchCount)) {
            lottoResult.put(Rank.BONUS, lottoResult.get(Rank.BONUS) + 1);
            lottoResult.put(Rank.SECOND, lottoResult.get(Rank.SECOND) - 1);
        }
    }

    private boolean isBonus(LottoNumber bonus, LottoTicket ticket, int matchCount) {
        return matchCount == BONUS_COUNT && ticket.contains(bonus);
    }

    public Map<Rank, Integer> getLottoResult() {
        return lottoResult;
    }
}
