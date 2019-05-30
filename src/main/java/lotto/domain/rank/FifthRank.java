package lotto.domain.rank;

public class FifthRank extends AbstractRank {
    private static final String FIFTH_WINNER_REWARD = "5,000";

    private final int reward;
    private int winnersNumber;

    public FifthRank() {
        this.reward = Integer.parseInt(FIFTH_WINNER_REWARD
                .replaceAll(",", ""));
        this.winnersNumber = Rank.ZERO;
    }

    public void addWinner() {
        winnersNumber++;
    }

    public long rewardMoney() {
        return super.rewardMoney(this.reward, this.winnersNumber);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("3개 일치, (")
                .append(reward)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
