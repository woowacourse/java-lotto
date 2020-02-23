package domain;

import java.util.*;

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

    public boolean containLottoNumber(LottoNumber lottoNumber) {
        return this.lottoTicket.stream()
                .anyMatch(ln -> ln.equals(lottoNumber));
    }

    public List<String> lottoTicketNumberToString() {
        List<String> ticketNumbers = new ArrayList<>();
        for (LottoNumber lottoNumber : this.lottoTicket) {
            ticketNumbers.add(lottoNumber.toString());
        }
        return ticketNumbers;
    }

    public List<LottoNumber> getLottoTicket() {
        return this.lottoTicket;
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
