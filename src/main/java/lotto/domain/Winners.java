package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Winners {
    public static final int ZERO = 0;
    private static final int PERCENT = 100;
    private Map<WinnerType, AbstractWinners> winners;

    public Winners() {
        this.winners = initWinners();
    }

    private Map<WinnerType, AbstractWinners> initWinners() {
        Map<WinnerType, AbstractWinners> winners = new LinkedHashMap<>();
        winners.put(WinnerType.FIFTH, new FifthWinners());
        winners.put(WinnerType.FOURTH, new FourthWinners());
        winners.put(WinnerType.THIRD, new ThirdWinners());
        winners.put(WinnerType.SECOND, new SecondWinners());
        winners.put(WinnerType.FIRST, new FirstWinners());
        return winners;
    }

    public void addWinner(int matchNumber) {
        WinnerType winnerType = WinnerType.valueOf(matchNumber);
        winners.get(winnerType).addWinner();
    }

    public long totalRewardMoney() {
        long totalRewardMoney = 0;
        for (AbstractWinners winners : winners.values()) {
            totalRewardMoney += winners.rewardMoney();
        }
        return totalRewardMoney;
    }

    public double rateOfReturn(int userMoney) {
        long totalRewardMoney = totalRewardMoney();
        return ((double) totalRewardMoney / userMoney) * PERCENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winners winners1 = (Winners) o;
        return Objects.equals(winners, winners1.winners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winners);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("당첨 통계\n")
                .append("---------\n");
        for (AbstractWinners value : winners.values()) {
            stringBuilder.append(value.toString());
        }
        return stringBuilder.toString();
    }
}
