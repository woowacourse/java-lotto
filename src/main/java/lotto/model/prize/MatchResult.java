package lotto.model.prize;

public class MatchResult {
    private final int count;
    private final boolean bonus;

    public MatchResult(int count, boolean bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public boolean isCount(int count) {
        return this.count == count;
    }

    public boolean isBonus() {
        return bonus;
    }
}
