package lotto.domain.result;

import lotto.domain.user.UserTickets;
import lotto.domain.winning.WinningLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WinningResult {
    private static final int PLUS_AMOUNT = 1;
    private static final int MINIMUM_PRICE = 0;
    private static final String ERROR_DIVIDE_ZERO = "총 수익률을 계산할 수 없습니다.";
    private static final int INITIAL_VALUE_ZERO = 0;
    private static final String ERROR_NULL_TICKETS = "WinningResult(UserTickets) has Null";
    private static final String ERROR_NULL_WINNING_LOTTO = "WinningResult(WinningLotto) has Null";
    private static final String ERROR_NULL_RANK = "getMatchedRankCountValue() has Null";
    private static final int CONVERT_PRICE_UNIT = 1000;

    private Map<Rank, Integer> matchedRankCount;

    public WinningResult(UserTickets tickets, WinningLotto winningLotto) {
        checkNullUserTickets(tickets);
        checkNullWinningLotto(winningLotto);
        this.matchedRankCount = makeMatchedRankCount(tickets, winningLotto);
    }

    private static void checkNullUserTickets(UserTickets tickets) {
        if (tickets == null) {
            throw new IllegalArgumentException(ERROR_NULL_TICKETS);
        }
    }

    private static void checkNullWinningLotto(WinningLotto winningLotto) {
        if (winningLotto == null) {
            throw new IllegalArgumentException(ERROR_NULL_WINNING_LOTTO);
        }
    }

    private Map<Rank, Integer> makeMatchedRankCount(UserTickets tickets, WinningLotto winningLotto) {
        Map<Rank, Integer> matchedRankCount = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(Function.identity(), value -> INITIAL_VALUE_ZERO));

        List<Rank> matchedRanks = new ArrayList<>(tickets.getMatchedRanks(winningLotto));
        matchedRanks.forEach(rank -> matchedRankCount.put(rank, plusMatchedCount(matchedRankCount, rank)));

        return matchedRankCount;
    }

    private int plusMatchedCount(Map<Rank, Integer> matchedRankCount, Rank rank) {
        return matchedRankCount.get(rank) + PLUS_AMOUNT;
    }

    public int getMatchedRankCountValue(Rank rank) {
        if (rank == null) {
            throw new IllegalArgumentException(ERROR_NULL_RANK);
        }

        return matchedRankCount.get(rank);
    }

    public double getTotalYield() {
        return getTotalRevenue() / getTotalPrice();
    }

    private double getTotalPrice() {
        int totalPrice = matchedRankCount.values().stream().mapToInt(Integer::intValue).sum() * CONVERT_PRICE_UNIT;
        if (totalPrice <= MINIMUM_PRICE) {
            throw new IllegalArgumentException(ERROR_DIVIDE_ZERO);
        }

        return (double) totalPrice;
    }

    private int getTotalRevenue() {
        return IntStream.of(Arrays.stream(Rank.values())
                .mapToInt(rank -> rank.getTotalRankWinningMoney(matchedRankCount.get(rank)))
                .toArray())
                .sum();
    }
}
