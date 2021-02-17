package lotto.domain;

import java.util.*;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public Map<PrizeType, Integer> checkWinningTickets(List<Integer> winningNumbers, int bonusBall) {
        Map<PrizeType, Integer> winningTickets = new HashMap<>();
        for (PrizeType prizeType : PrizeType.values()) {
            winningTickets.put(prizeType, getTicketPrizeTypeCount(winningNumbers, bonusBall, prizeType));
        }
        return winningTickets;
    }

    private int getTicketPrizeTypeCount(List<Integer> numbers, int bonusBall, PrizeType prizeType) {
        int count = 0;
        for (LottoTicket lottoTicket : lottoTickets) {
            count += addPrize(lottoTicket.getMatchingCount(numbers, bonusBall), prizeType);
        }
        return count;
    }

    private int addPrize(double matchingCount, PrizeType prizeType) {
        if (prizeType.isEqualToMatchCount(matchingCount)) {
            return 1;
        }
        return 0;
    }

    public double calculateProfitRate(Money money, Map<PrizeType, Integer> result) {
        int moneySum = 0;
        for (PrizeType prizeType : result.keySet()) {
            moneySum += prizeType.getPrizeMoney().getValue() * result.get(prizeType);
        }
        return (double) moneySum / money.getValue();
    }
}
