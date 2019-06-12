package lotto.domain;

public class Result {
    private final int matchCount;
    private final int money;
    private final int matchLottoCount;

    public Result(Rank rank, Calculator calculator) {
        this.matchCount = rank.getMatchCount();
        this.money = rank.getMoney();
        this.matchLottoCount = calculator.getMatchlottoCountPerRank(rank);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMoney() {
        return money;
    }

    public int getMatchLottoCount() {
        return matchLottoCount;
    }
}
