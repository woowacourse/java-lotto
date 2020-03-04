package lotto.domain;

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
        return (int) this.lottoTicket.stream()
                .filter(lottoNumber -> lottoTicketToCompare.getLottoTicket()
                        .contains(lottoNumber))
                .count();
    }

    public Set<LottoNumber> getLottoTicket() {
        return lottoTicket;
    }
}
