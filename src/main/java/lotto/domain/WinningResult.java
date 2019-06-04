package lotto.domain;

import lotto.domain.lottomanager.BonusBall;
import lotto.domain.user.UserTickets;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WinningResult {
    private static final int PLUS_AMOUNT = 1;
    private static final int MINIMUM_PURCHASE_PRICE = 0;
    private static final String ERROR_DIVIDE_ZERO = "총 수익률을 계산할 수 없습니다.";
    private static final int INITIAL_VALUE_ZERO = 0;

    private Map<Rank, Integer> matchedRankCount;

    private WinningResult(UserTickets userTickets, WinningLotto winningLotto, BonusBall bonusBall) {
        this.matchedRankCount = makeMatchedRankCount(userTickets, winningLotto, bonusBall);
    }

    public static WinningResult createWinningResult(UserTickets userTickets, WinningLotto winningLotto, BonusBall bonusBall) {
        return new WinningResult(userTickets, winningLotto, bonusBall);
    }

    private Map<Rank, Integer> makeMatchedRankCount(UserTickets userTickets, WinningLotto winningLotto, BonusBall bonusBall) {
        matchedRankCount = initializeMatchedRankCount();
        fillMatchedRankCount(userTickets, winningLotto, bonusBall);

        return matchedRankCount;
    }

    private Map<Rank, Integer> initializeMatchedRankCount() {
        return Arrays.stream(Rank.values())
                .collect(Collectors.toMap(Function.identity(), value -> INITIAL_VALUE_ZERO, (p1, p2) -> p1));
    }

    private void fillMatchedRankCount(UserTickets userTickets, WinningLotto winningLotto, BonusBall bonusBall) {
        for (Rank rank : userTickets.getMatchedRanks(winningLotto, bonusBall)) {
            matchedRankCount.put(rank, plusMatchedCount(rank));
        }
    }

    private Integer plusMatchedCount(Rank rank) {
        return matchedRankCount.get(rank) + PLUS_AMOUNT;
    }

    public Integer getMatchedRankCountValue(Rank rank) {
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
        return IntStream.of(Arrays.stream(Rank.values())
                .mapToInt(rank -> rank.getTotalRankWinningMoney(matchedRankCount.get(rank)))
                .toArray())
                .sum();
    }
}
