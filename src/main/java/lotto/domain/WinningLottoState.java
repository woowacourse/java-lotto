package lotto.domain;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class WinningLottoState {
    private Map<Rank, Integer> winningLottoState;

    WinningLottoState(Map<Rank, Integer> winningLottoState) {
        this.winningLottoState = winningLottoState;
    }

    public int countOfRank(Rank rank) {
        return winningLottoState.get(rank);
    }

    public Set<Rank> getWinningLottoStateKeySet() {
        return winningLottoState.keySet();
    }

    public double getYield(Money money) {
        LongAdder longAdder = new LongAdder();
        winningLottoState.entrySet().stream()
                .map(entry -> (long) (entry.getKey().getWinningMoney() * entry.getValue()))
                .collect(Collectors.toList())
                .forEach(longAdder::add)
        ;
        return money.calculateYield(longAdder.longValue());
    }
}