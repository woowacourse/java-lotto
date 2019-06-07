package lotto.domain.rank;

public class FourthRank extends AbstractRank {
    private static final int FOURTH_WINNER_REWARD = 50_000;

    public long rewardMoney() {
        return (long) FOURTH_WINNER_REWARD * winnersNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("4개 일치, (")
                .append(FOURTH_WINNER_REWARD)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
