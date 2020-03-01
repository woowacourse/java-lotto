package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    private static final int LOTTO_TICKET_SIZE = 6;

    private Set<LottoNumber> lottoNumbers;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket from(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        return new LottoTicket(lottoNumbers);
    }

    private static void validateSize(Set<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또는 중복되지 않는 6개의 숫자로 이뤄져야 합니다.");
        }
    }

    public int countMatches(LottoTicket targetTicket) {
        return (int) lottoNumbers.stream()
                .filter(targetTicket::contains)
                .count();
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        List<LottoNumber> lottoTicketList = new ArrayList<>(lottoNumbers);
        Collections.sort(lottoTicketList);
        return lottoTicketList.toString();
    }
}
