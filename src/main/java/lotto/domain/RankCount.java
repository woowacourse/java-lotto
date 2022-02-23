package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RankCount {

    private final Map<Rank, Integer> rankCount;

    public RankCount(Tickets tickets, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> rankCount.put(rank, 0));
        this.rankCount = rankCount;
        parseRank(tickets, winningNumbers, bonusNumber);
    }

    private void parseRank(Tickets tickets, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        for (Ticket ticket : tickets.getTickets()) {
            increaseCount(ticket.getRank(winningNumbers, bonusNumber));
        }
    }

    private void increaseCount(Rank rank) {
        rankCount.put(rank, rankCount.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return rankCount.get(rank);
    }
}
