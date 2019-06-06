package lotto.domain;

import lotto.domain.lottomanager.BonusBall;
import lotto.domain.user.UserTickets;
import lotto.utils.NullCheckUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private WinningResult(UserTickets tickets, WinningLotto winningLotto, BonusBall bonus) {
        this.matchedRankCount = makeMatchedRankCount(tickets, winningLotto, bonus);
    }

    private Map<Rank, Integer> makeMatchedRankCount(UserTickets tickets, WinningLotto winningLotto, BonusBall bonus) {
        Map<Rank, Integer> temp = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(Function.identity(), value -> INITIAL_VALUE_ZERO));
        List<Rank> ranks = new ArrayList<>(tickets.getMatchedRanks(winningLotto, bonus));
        for(Rank rank : ranks){
            temp.put(rank, temp.get(rank) + 1);
        }

        return temp;
    }

    private Map<Rank, Integer> initializeMatchedRankCount() {
        return Arrays.stream(Rank.values())
                .collect(Collectors.toMap(Function.identity(), value -> INITIAL_VALUE_ZERO));
    }

    private Map<Rank, Integer> fillMatchedRankCount(UserTickets tickets, WinningLotto winningLotto, BonusBall bonus) {
        return tickets.getMatchedRanks(winningLotto, bonus)
                .stream()
                .collect(Collectors.toMap(Function.identity(), this::plusMatchedCount));
    }

    private Integer plusMatchedCount(Rank rank) {
        return matchedRankCount.get(rank) + PLUS_AMOUNT;
    }

    public static WinningResult createWinningResult(UserTickets tickets, WinningLotto winningLotto, BonusBall bonus) {
        NullCheckUtil.checkNullUserTickets(tickets);
        NullCheckUtil.checkNullWinningLotto(winningLotto);
        NullCheckUtil.checkNullBonusBall(bonus);
        return new WinningResult(tickets, winningLotto, bonus);
    }

    public Integer getMatchedRankCountValue(Rank rank) {
        NullCheckUtil.checkNullRank(rank);
        return matchedRankCount.get(rank);
    }

    public Double getTotalYield(Integer purchasePrice) {
        NullCheckUtil.checkNullInteger(purchasePrice);
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
