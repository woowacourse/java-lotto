package lotto.dto;

import java.util.List;

public class StatusDto {
    private final int lottoRound;
    private final List<String> userLotto;
    private final String winningLotto;
    private final int bonus;
    private final List<String> results;
    private final String returnRate;
    private final int prize;


    public StatusDto(int lottoRound, List<String> userLotto, String winningLotto, int bonus, List<String> results, String returnRate, int prize) {
        this.lottoRound = lottoRound;
        this.userLotto = userLotto;
        this.winningLotto = winningLotto;
        this.bonus = bonus;
        this.results = results;
        this.returnRate = returnRate;
        this.prize = prize;
    }

    public int getLottoRound() {
        return lottoRound;
    }

    public List<String> getUserLotto() {
        return userLotto;
    }

    public String getWinningLotto() {
        return winningLotto;
    }

    public int getBonus() {
        return bonus;
    }

    public List<String> getResults() {
        return results;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public int getPrize() {
        return prize;
    }
}
