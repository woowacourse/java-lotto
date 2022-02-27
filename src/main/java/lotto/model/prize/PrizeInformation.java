package lotto.model.prize;

import java.util.List;

public class PrizeInformation {
    private final Prize prize;
    private final int count;

    private PrizeInformation(Prize prize, int count) {
        this.prize = prize;
        this.count = count;
    }

    public static PrizeInformation of(List<MatchResult> matchResults, Prize prize) {
        int count = (int) matchResults.stream()
                .filter(matchResult -> Prize.getPrize(matchResult) == prize)
                .count();
        return new PrizeInformation(prize, count);
    }

    public int pickAmount() {
        return this.prize.pickAmount(count);
    }

    public Prize getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }
}
