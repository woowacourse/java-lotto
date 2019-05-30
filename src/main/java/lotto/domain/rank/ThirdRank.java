package lotto.domain.rank;

public class ThirdRank extends AbstractRank {
    private static final String THIRD_WINNER_REWARD = "1,500,000";

    private final int reward;
    private int winnersNumber;

    public ThirdRank() {
        this.reward = Integer.parseInt(THIRD_WINNER_REWARD
                .replaceAll(",", ""));
        this.winnersNumber = Rank.ZERO;
    }

    public void addWinner() {
        winnersNumber++;
    }

    public long rewardMoney() {
        return super.rewardMoney(reward, winnersNumber);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("5개 일치, (")
                .append(reward)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
