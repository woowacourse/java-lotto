package lotto.domain;

import java.util.Collections;
import java.util.Set;

public class LottoTicket {

    private static final int LOTTO_LENGTH = 6;
    private static final String LOTTO_NUMBER_ERROR_MESSAGE = "로또의 숫자가 중복되었거나, 6개가 아닙니다.";
    private Set<LottoNumber> lottoTicket;

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        this.lottoTicket = lottoNumbers;
    }

    private void validateLottoLength(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(LOTTO_NUMBER_ERROR_MESSAGE);
        }
    }

    boolean isContain(LottoNumber bonusNumber) {
        return lottoTicket.contains(bonusNumber);
    }

    public int countSameNumbers(LottoTicket lottoTicketToCompare) {
        int hitCount = 0;
        for (LottoNumber lottoNumber : lottoTicketToCompare.getLottoTicket()) {
            if (this.lottoTicket.contains(lottoNumber)) {
                hitCount++;
            }
        }
        return hitCount;
    }

    public Set<LottoNumber> getLottoTicket() {
        return Collections.unmodifiableSet(lottoTicket);
    }
}
