package lotto.model.prize;

/*
 * 당첨 번호와 구입한 로또의 대조 결과를 담는 Class
 */
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
