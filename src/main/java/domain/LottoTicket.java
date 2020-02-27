package domain;

import java.util.*;

public class LottoTicket {
    private static final int MAX_LOTTO_TICKET_SIZE = 6;

    private List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        validateLottoTicketSize(lottoTicket);
        validateDuplicateLottoNumber(lottoTicket);
        Collections.sort(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    public List<LottoNumber> getLottoTicket() {
        return this.lottoTicket;
    }

    private void validateDuplicateLottoNumber(List<LottoNumber> lottoTicket) {
        Set<LottoNumber> duplicateLottoTicket = new HashSet<>(lottoTicket);
        if (duplicateLottoTicket.size() != lottoTicket.size()) {
            throw new IllegalArgumentException("중복된 로또 숫자를 입력하였습니다.");
        }
    }

    private void validateLottoTicketSize(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != MAX_LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 숫자의 개수가 잘못되었습니다.");
        }
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }
}
