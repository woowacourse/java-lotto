package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;
    public static final int LOTTO_PRICE = 1000;
    private static final String WRONG_SIZE_EXCEPTION_MESSAGE = "로또 번호의 갯수가 %d개로 올바르지 않습니다.";

    private final Set<LottoBall> lottoBalls;

    public LottoTicket(List<Integer> numbers) {
        Set<LottoBall> lottoBalls = LottoBallFactory.collectLottoBalls(numbers);
        validateSize(lottoBalls);
        this.lottoBalls = lottoBalls;
    }

    private void validateSize(Set<LottoBall> lottoBalls) {
        int size = lottoBalls.size();
        if (size != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(String.format(WRONG_SIZE_EXCEPTION_MESSAGE, size));
        }
    }

    public boolean has(LottoBall bonusBall) {
        return this.lottoBalls.contains(bonusBall);
    }

    public Set<LottoBall> getLottoBalls() {
        return Collections.unmodifiableSet(lottoBalls);
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
