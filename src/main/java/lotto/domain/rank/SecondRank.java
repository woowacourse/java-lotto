package lotto.domain.rank;

public class SecondRank extends AbstractRank {
    private static final int SECOND_WINNER_REWARD = 30_000_000;

    public long rewardMoney() {
        return (long) SECOND_WINNER_REWARD * winnersNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("5개 일치, 보너스 볼 일치(")
                .append(SECOND_WINNER_REWARD)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
