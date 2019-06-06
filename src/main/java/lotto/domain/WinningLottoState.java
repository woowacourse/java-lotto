package lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class WinningLottoState {
    private Map<Rank, Integer> winningLottoState;

    public WinningLottoState(WinningLotto winningLotto, LottoTickets lottoTickets) {
        initWinningLottoState();
        for (int index = 0; index < lottoTickets.size(); index++) {
            LottoTicket lottoTicket = lottoTickets.get(index);
            increaseCount(winningLotto.calRank(lottoTicket));
        }
    }

    private void initWinningLottoState() {
        winningLottoState = new TreeMap<>(Collections.reverseOrder());
        for (Rank rank : Rank.values()) {
            winningLottoState.put(rank, 0);
        }
    }

    private void increaseCount(Rank rank) {
        winningLottoState.put(rank, winningLottoState.get(rank) + 1);
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
        return money.calYield(longAdder.longValue());
    }
}