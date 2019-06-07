package lotto.domain.rank;

public class FifthRank extends AbstractRank {
    private static final int FIFTH_WINNER_REWARD = 5_000;

    public long rewardMoney() {
        return (long) FIFTH_WINNER_REWARD * winnersNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("3개 일치, (")
                .append(FIFTH_WINNER_REWARD)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
