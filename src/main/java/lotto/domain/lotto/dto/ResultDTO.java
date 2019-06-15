package lotto.domain.lotto.dto;

import lotto.domain.Rank;

public class ResultDTO {
    private int countOfMatch;
    private long winningMoney;
    private int lottoScore;

    public ResultDTO(Rank rank, int lottoScore) {
        this.countOfMatch = rank.getCountOfMatch();
        this.winningMoney = rank.getWinningMoney();
        this.lottoScore = lottoScore;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public long getWinningMoney() {
        return winningMoney;
    }

    public int getLottoScore() {
        return lottoScore;
    }
}
