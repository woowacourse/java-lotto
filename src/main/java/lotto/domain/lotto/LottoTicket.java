package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final String DUPLICATE_LOTTO_NUMBER = "로또는 중복되지 않는 6개의 숫자로 이뤄져야 합니다.";
    private static final int LOTTO_TICKET_SIZE = 6;

    private Set<LottoNumber> lottoTicket;

    LottoTicket(Set<Integer> lottoNumbers) {
        Set<LottoNumber> lottoTicket = lottoNumbers.stream()
                .map(LottoNumber::publishLottoNumber)
                .collect(Collectors.toSet());
        validateSize(lottoTicket);
        this.lottoTicket = Collections.unmodifiableSet(lottoTicket);
    }

    private void validateSize(Set<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER);
        }
    }

    int countMatches(LottoTicket targetTicket) {
        return (int) lottoTicket.stream()
                .filter(targetTicket::contains)
                .count();
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoTicket that = (LottoTicket)o;
        return Objects.equals(lottoTicket, that.lottoTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTicket);
    }

    @Override
    public String toString() {
        List<LottoNumber> lottoTicketList = new ArrayList<>(lottoTicket);
        Collections.sort(lottoTicketList);
        return lottoTicketList.toString();
    }
}
