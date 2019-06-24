package lotto.dto;

import java.util.List;

public class ResultLottoDTO {
    private String winningLotto;
    private List<String> rank;
    private int prize;
    private int incomeRate;
    private int bonusBall;

    public String getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(String winningLotto) {
        this.winningLotto = winningLotto;
    }

    public List<String> getRank() {
        return rank;
    }

    public void setRank(List<String> rank) {
        this.rank = rank;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(int incomeRate) {
        this.incomeRate = incomeRate;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public void setBonusBall(int bonusBall) {
        this.bonusBall = bonusBall;
    }
}
