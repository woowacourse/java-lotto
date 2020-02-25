package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.util.NullOrEmptyValidator;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_BALL_COUNT = 6;
    private static final String WRONG_SIZE_EXCEPTION_MESSAGE = "로또 번호의 갯수가 %d개로 올바르지 않습니다.";

    private final Set<LottoBall> lottoBalls;

    public LottoTicket(Set<LottoBall> lottoBalls) {
        NullOrEmptyValidator.isNullOrEmpty(lottoBalls);
        validateSize(lottoBalls);

        this.lottoBalls = lottoBalls;
    }

    private void validateSize(Set<LottoBall> lottoBalls) {
        int size = lottoBalls.size();
        if (size != LOTTO_BALL_COUNT) {
            throw new IllegalArgumentException(String.format(WRONG_SIZE_EXCEPTION_MESSAGE, size));
        }
    }

    public boolean has(LottoBall lottoBall) {
        NullOrEmptyValidator.isNull(lottoBall);

        return this.lottoBalls.contains(lottoBall);
    }

    public Set<LottoBall> getLottoBalls() {
        return Collections.unmodifiableSet(this.lottoBalls);
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

    @Override
    public String toString() {
        return "LottoTicket{" +
                "lottoBalls=" + lottoBalls +
                '}';
    }
}
