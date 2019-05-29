package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Winners {
    private static final int ZERO = 0;
    private Map<WinnerType, Integer> winners;

    public Winners() {
        this.winners = initWinners();
    }

    private Map<WinnerType, Integer> initWinners() {
        Map<WinnerType, Integer> winners = new HashMap<>();
        winners.put(WinnerType.FIRST, ZERO);
        winners.put(WinnerType.SECOND, ZERO);
        winners.put(WinnerType.THIRD, ZERO);
        winners.put(WinnerType.FOURTH, ZERO);
        winners.put(WinnerType.FIFTH, ZERO);
        return winners;
    }

    public void addWinner(int matchNumber) {
        WinnerType winner = WinnerType.valueOf(matchNumber);
        winners.put(winner, winners.get(winner) + 1);
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
}
