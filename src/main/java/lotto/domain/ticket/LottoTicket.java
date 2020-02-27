package lotto.domain.ticket;

import lotto.domain.ticket.number.LottoNumber;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;
    public static final int LOTTO_PRICE = 1000;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(Set<LottoNumber> balls) {
        validateSize(balls);
        this.lottoNumbers = balls;
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        int size = lottoNumbers.size();
        if (size != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(String.format("로또 번호의 갯수가 %d개로 올바르지 않습니다.", size));
        }
    }

    public boolean has(LottoNumber bonusBall) {
        return this.lottoNumbers.contains(bonusBall);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return "LottoTicket{" +
                "lottoBalls=" + lottoNumbers +
                '}';
    }
}
