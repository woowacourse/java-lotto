package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {

    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false),
    NO_RANK(0, 0, false);

    private double winningMoney;
    private long winningCount;
    private boolean hitBonusBall;

    private Rank(long winningMoney, long winningCount, boolean hitBonusBall) {
        this.winningMoney = winningMoney;
        this.winningCount = winningCount;
        this.hitBonusBall = hitBonusBall;
    }

    public static Map<Rank, Long> calculateEachRankCount(WinningTicket winningTicket) {
        Map<Rank, Long> eachRankCount = new HashMap<>();
        List<Rank> lottoTicketRank = generateTicketRank(winningTicket);

        for (Rank r : values()) {
            long rankCount = lottoTicketRank.stream()
                    .filter(lottoTicket -> lottoTicket == r && lottoTicket != Rank.NO_RANK)
                    .count();
            eachRankCount.put(r, rankCount);
        }
        return eachRankCount;
    }

    private static List<Rank> generateTicketRank(WinningTicket winningTicket) {
        return LottoTickets.getLottoTickets()
                .stream()
                .map(lottoTicket -> determineRank(winningTicket.hitLottoBall(lottoTicket)
                        , winningTicket.hitBonusBall(lottoTicket)))
                .collect(Collectors.toList());
    }

    private static Rank determineRank(long hitCount, boolean hitBonusBall) {
        return Arrays.stream(values())
                .filter(rank -> rank.winningCount == hitCount && isHitBonusBall(hitBonusBall, rank))
                .findFirst()
                .orElse(Rank.NO_RANK);
    }

    private static boolean isHitBonusBall(boolean hitBonusBall, Rank rank) {
        return rank.hitBonusBall == hitBonusBall;
    }

    public long getWinningCount() {
        return this.winningCount;
    }

    public double getWinningMoney() {
        return this.winningMoney;
    }
}
