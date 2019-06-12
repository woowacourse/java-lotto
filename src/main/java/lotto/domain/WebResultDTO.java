package lotto.domain;

import java.util.List;

public class WebResultDTO {
    public String winningLotto;
    public String interestRate;
    public String prize;
    public List<String> lottos;
    public List<String> result;
    public List<String> rounds;
    public String round;

    public List<String> getRounds() {
        return rounds;
    }

    public void setRounds(List<String> rounds) {
        this.rounds = rounds;
    }

    public String getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(String winningLotto) {
        this.winningLotto = winningLotto;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public List<String> getLottos() {
        return lottos;
    }

    public void setLottos(List<String> lottos) {
        this.lottos = lottos;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
