package lotto.domain;

public class SecondWinners extends AbstractWinners {
    private static final String SECOND_WINNER_REWARD = "30,000,000";

    private final int reward;
    private int winnersNumber;

    public SecondWinners() {
        this.reward = Integer.parseInt(SECOND_WINNER_REWARD
                .replaceAll(",", ""));
        this.winnersNumber = Winners.ZERO;
    }

    public void addWinner() {
        winnersNumber++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("5개 일치, 보너스 볼 일치(")
                .append(reward)
                .append("원) - ")
                .append(winnersNumber)
                .append("개\n")
        ;
        return stringBuilder.toString();
    }
}
