package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoTicket {
    private List<LottoBall> lottoBalls;

    public LottoTicket(List<LottoBall> lottoBalls) {
        this.lottoBalls = lottoBalls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoBalls, that.lottoBalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoBalls);
    }
}
