package lotto.domain.rank;

public class ThirdRank extends AbstractRank {
    private static final int THIRD_WINNER_REWARD = 1_500_000;

    public long rewardMoney() {
        return (long) THIRD_WINNER_REWARD * winnersNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("5개 일치, (")
                .append(THIRD_WINNER_REWARD)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
