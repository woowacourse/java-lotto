package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    private static final int LOTTO_TICKET_SIZE = 6;

    private List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        validateLottoTicketSize(lottoTicket);
        validateDuplicate(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void validateLottoTicketSize(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoTicket) {
        Set<LottoNumber> duplicateLottoNumber = new HashSet<>(lottoTicket);
        if (duplicateLottoNumber.size() != lottoTicket.size()) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }

    public boolean contains(int bonusNumber) {
        return this.lottoTicket.stream()
                .anyMatch(ln -> ln.isSameLottoNumber(bonusNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoTicket, that.lottoTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTicket);
    }
}
