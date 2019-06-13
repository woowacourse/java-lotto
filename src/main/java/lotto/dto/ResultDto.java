package lotto.dto;

import java.util.Map;

public class ResultDto {
    private Map<String, Integer> prize;
    private int winningMoney;

    public Map<String, Integer> getPrize() {
        return prize;
    }

    public void setPrize(final Map<String, Integer> prize) {
        this.prize = prize;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public void setWinningMoney(final int winningMoney) {
        this.winningMoney = winningMoney;
    }
}
