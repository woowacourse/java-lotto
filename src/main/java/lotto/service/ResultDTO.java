package lotto.service;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultDTO {
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

    public void setRounds(List<Integer> rounds) {
        this.rounds = rounds.stream().map(n -> String.valueOf(n)).collect(Collectors.toList());
    }

    public String getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = outputWinningLotto(winningLotto);
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = String.valueOf(prize);
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = String.valueOf(interestRate);
    }

    public List<String> getLottos() {
        return lottos;
    }

    public void setLottos(Lottos lottos) {
        this.lottos = outputLottos(lottos);
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(WinningStatistics result) {
        this.result = outputResult(result);
    }

    public String getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = String.valueOf(round);
    }

    private static String outputLotto(Lotto lotto) {
        String result = "[";
        List<String> lottoNumbers = lotto.getLottoNumbers()
                .stream()
                .map(n -> String.valueOf(n.getNumber()))
                .collect(Collectors.toList());
        result += String.join(", ", lottoNumbers);
        return result + "]";
    }

    public static String outputWinningLotto(WinningLotto winningLotto) {
        return outputLotto(winningLotto.getLotto()) + " + " + winningLotto.getBonus().getNumber();
    }

    public static List<String> outputLottos(Lottos lottos) {
        return lottos.getLottos().stream().map(lotto -> outputLotto(lotto)).collect(Collectors.toList());
    }

    public static List<String> outputResult(WinningStatistics winningStatistics) {
        List<String> result = new ArrayList<>();
        Map<Rank, Integer> statistics = winningStatistics.getStatistics();

        for (Rank rank : Rank.values()) {
            if (statistics.get(rank) > 0) {
                if (rank.equals(Rank.MISS)) {
                    result.add(String.format("꽝(%d원): %d회", rank.getWinningMoney(), statistics.get(rank)));
                } else {
                    result.add(String.format("%d등(%d원): %d회", rank.ordinal() + 1, rank.getWinningMoney(), statistics.get(rank)));
                }
            }
        }
        return result;
    }
}
