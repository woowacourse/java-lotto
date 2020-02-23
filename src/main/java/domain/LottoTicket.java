package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    private List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        validateLottoTicketSize(lottoTicket);
        validateDuplicate(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void validateLottoTicketSize(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoTicket) {
        Set<LottoNumber> duplicateLottoNumber = new HashSet<>();
        if (duplicateLottoNumber.size() != lottoTicket.size()) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }
}
