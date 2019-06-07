package lotto.domain.rank;

public class FirstRank extends AbstractRank {
    private static final int FIRST_WINNER_REWARD = 2_000_000_000;

    public long rewardMoney() {
        return (long) FIRST_WINNER_REWARD * winnersNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("6개 일치, (")
                .append(FIRST_WINNER_REWARD)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
