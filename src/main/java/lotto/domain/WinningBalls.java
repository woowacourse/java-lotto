package lotto.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WinningBalls {
    private final Set<LottoBall> lottoBalls;

    public WinningBalls(Set<LottoBall> lottoBalls, LottoBall bonusBall) {
        this.lottoBalls = new HashSet<>(lottoBalls);
        this.lottoBalls.add(bonusBall);
    }

    public int getMatchCount(LottoTicket lottoTicket) {
        return (int) this.lottoBalls.stream()
                .filter(lottoTicket::has)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningBalls that = (WinningBalls) o;
        return Objects.equals(lottoBalls, that.lottoBalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoBalls);
    }
}
