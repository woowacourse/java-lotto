package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;

import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    public static final int LOTTO_PRICE = 1000;
    private static final String WRONG_SIZE_EXCEPTION_MESSAGE = "로또 번호의 갯수가 %d개로 올바르지 않습니다.";
    private Set<LottoBall> lottoBalls;

    public LottoTicket(Set<LottoBall> lottoBalls) {
        validateSize(lottoBalls);
        this.lottoBalls = lottoBalls;
    }

    private void validateSize(Set<LottoBall> lottoBalls) {
        int size = lottoBalls.size();
        if (size != 6) {
            throw new IllegalArgumentException(String.format(WRONG_SIZE_EXCEPTION_MESSAGE, size));
        }
    }

    public int getMatchCount(LottoTicket lottoTicket) {
        return (int) this.lottoBalls.stream()
                .filter(lottoTicket::has)
                .count();
    }

    public boolean has(LottoBall bonusBall) {
        return this.lottoBalls.contains(bonusBall);
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
