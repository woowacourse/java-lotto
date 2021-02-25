package domain;

import java.util.List;

public class Ranks {

    private final List<Rank> ranks;

    public Ranks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int count(Rank rank) {
        return (int) ranks.stream()
                .filter(current -> current == rank)
                .count();
    }

    public int totalProfit() {
        return ranks.stream()
                .mapToInt(Rank::getMoney)
                .sum();
    }
}
