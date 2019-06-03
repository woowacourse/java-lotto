package lotto.domain;

import java.util.Map;

public class LottoGame {

    private final User user;
    private final Map<Rank, Integer> countOfRank;

    public LottoGame(User user, WinningLotto winningLotto) {
        this.user = user;
        this.countOfRank = user.calculateCountOfRank(winningLotto);
    }

    public Map<Rank, Integer> getCountOfRank() {
        return this.countOfRank;
    }

    public Prize calculatePrize() {
        Prize prize = new Prize();
        countOfRank.forEach((rank, count) -> prize.addPrize(rank.calculatePrizeOfThisRank(count)));
        return prize;
    }

    public double calculateRateOfReturn() {
        return user.calculateRateOfReturn(calculatePrize());
    }
}
