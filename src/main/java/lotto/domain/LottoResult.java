package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int BONUS_CONDITION = 5;

    private Map<Rank, Integer> result = new HashMap<>();

    public Map<Rank, Integer> matchResult(WinningTicket winningTicket, LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            int matchCount = winningTicket.compare(lottoTicket);
            addBonus(winningTicket, lottoTicket, matchCount);
            Rank rank = Rank.find(matchCount);
            updateResult(rank);
        }
        return result;
    }

    private void addBonus(WinningTicket winningTicket, LottoTicket lottoTicket, int matchCount) {
        if (matchCount == BONUS_CONDITION && lottoTicket.contains(winningTicket.getBonusNumber())) {
            updateResult(Rank.BONUS);
            removeBonusAddedFromResult();
        }
    }

    private void removeBonusAddedFromResult() {
        result.put(Rank.SECOND, result.getOrDefault(Rank.SECOND, 0) - 1);
    }

    private void updateResult(Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    public String calculateRate(Money money) {
        double total = 0;
        for (Rank rank : result.keySet()) {
            total += rank.getPrize() * result.get(rank);
        }
        double rate = total * 100 / (money.calculateTicketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }

    Map<Rank, Integer> getResult() {
        return result;
    }

}
