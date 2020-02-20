package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {
    private static final int BONUS_CONDITION = 5;
    private List<LottoTicket> lottoTickets;

    LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets createLottoTickets(Money money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < money.ticketQuantity(); i++) {
            lottoTickets.add(LottoTicket.create());
        }
        return new LottoTickets(lottoTickets);
    }

    public Map<Rank, Integer> matchResult(LottoTicket winner, LottoNumber bonus) {
        int matchCount;
        for (LottoTicket lotto : lottoTickets) {
            matchCount = winner.compare(lotto);
            addBonus(bonus, matchCount, lotto);
            Rank rank = Rank.find(matchCount);
            updateResult(Rank.result, rank);
        }
        return Rank.result;
    }

    private void addBonus(LottoNumber bonus, int matchCount, LottoTicket lotto) {
        if (matchCount == BONUS_CONDITION && lotto.contains(bonus)) {
            updateResult(Rank.result, Rank.BONUS);
            removeBonusAddedFromResult(Rank.result);
        }
    }

    private void updateResult(Map<Rank, Integer> result, Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    private void removeBonusAddedFromResult(Map<Rank, Integer> result) {
        result.put(Rank.SECOND, result.getOrDefault(Rank.SECOND, 0) - 1);
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
            .map(LottoTicket::toString)
            .collect(Collectors.joining("\n"));
    }
}
