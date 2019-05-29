package lotto.domain;

public class FirstWinners extends AbstractWinners {
    private static final String FIRST_WINNER_REWARD = "2,000,000,000";

    private final int reward;
    private int winnersNumber;

    public FirstWinners() {
        this.reward = Integer.parseInt(FIRST_WINNER_REWARD
                .replaceAll(",", ""));
        this.winnersNumber = Winners.ZERO;
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
        stringBuilder.append("6개 일치, (")
                .append(reward)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
