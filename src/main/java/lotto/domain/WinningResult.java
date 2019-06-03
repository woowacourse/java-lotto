package lotto.domain;

import lotto.domain.user.UserTickets;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WinningResult {
    private static final int PLUS_AMOUNT = 1;
    private static final int MINIMUM_MATCHING_COUNT = 0;
    private static final int MAXIMUM_MATCHING_COUNT = 6;
    private static final int MINIMUM_PURCHASE_PRICE = 0;
    private static final String ERROR_DIVIDE_ZERO = "총 수익률을 계산할 수 없습니다.";
    private static final int INITIAL_VALUE_ZERO = 0;

    private Map<Rank, Integer> matchedRankCount;

    private WinningResult(UserTickets userTickets, WinningLotto winningLotto) {
        this.matchedRankCount = makeMatchedRankCount(userTickets, winningLotto);
    }

    public static WinningResult createWinningResult(UserTickets userTickets, WinningLotto winningLotto) {
        return new WinningResult(userTickets, winningLotto);
    }

    private Map<Rank, Integer> makeMatchedRankCount(UserTickets userTickets, WinningLotto winningLotto) {
        matchedRankCount = initializeMatchedRankCount();
        fillMatchedRankCount(userTickets, winningLotto);

        return matchedRankCount;
    }

    private Map<Rank, Integer> initializeMatchedRankCount() {
        return IntStream.rangeClosed(MINIMUM_MATCHING_COUNT, MAXIMUM_MATCHING_COUNT)
                .boxed()
                .collect(Collectors.toMap(Rank::valueOf, value -> INITIAL_VALUE_ZERO, (p1, p2) -> p1));
    }

    private void fillMatchedRankCount(UserTickets userTickets, WinningLotto winningLotto) {
        for (Rank rank : userTickets.getMatchedRanks(winningLotto)) {
            matchedRankCount.put(rank, plusMatchedCount(rank));
        }
    }

    private int plusMatchedCount(Rank rank) {
        return matchedRankCount.get(rank) + PLUS_AMOUNT;
    }

    public int getMatchedRankCountValue(Rank rank) {
        return matchedRankCount.get(rank);
    }

    public Double getTotalYield(Integer purchasePrice) {
        checkPurchasePrice(purchasePrice);
        return getTotalRevenue() / Double.valueOf(purchasePrice);
    }

    private void checkPurchasePrice(Integer purchasePrice) {
        if (purchasePrice <= MINIMUM_PURCHASE_PRICE) {
            throw new IllegalArgumentException(ERROR_DIVIDE_ZERO);
        }
    }

    private int getTotalRevenue() {
        return IntStream.rangeClosed(MINIMUM_MATCHING_COUNT, MAXIMUM_MATCHING_COUNT)
                .mapToObj(Rank::valueOf)
                .mapToInt(rank -> rank.getTotalRankWinningMoney(matchedRankCount.get(rank)))
                .sum();
    }
}
