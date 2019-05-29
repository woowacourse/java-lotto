package lotto.domain;

public class FourthWinners extends AbstractWinners {
    private static final String FOURTH_WINNER_REWARD = "50,000";

    private final int reward;
    private int winnersNumber;

    public FourthWinners() {
        this.reward = Integer.parseInt(FOURTH_WINNER_REWARD
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
        stringBuilder.append("4개 일치, (")
                .append(reward)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
