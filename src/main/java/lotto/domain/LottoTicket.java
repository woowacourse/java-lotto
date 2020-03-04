package lotto.domain;

import java.util.Set;

public class LottoTicket {

    private static final int LOTTO_LENGTH = 6;
    private static final String LOTTO_NUMBER_ERROR_MESSAGE = "로또의 숫자가 중복되었거나, 6개가 아닙니다.";
    public static final String NULL_INPUT_ERROR_MESSAGE = "올바르지 않은 접근/입력 입니다.";
    private Set<LottoNumber> lottoTicket;

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        if(lottoNumbers == null || lottoNumbers.isEmpty()){
            throw new NullPointerException(NULL_INPUT_ERROR_MESSAGE);
        }
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

    int countSameNumbers(LottoTicket lottoTicketToCompare) {
        return (int) this.lottoTicket.stream()
                .filter(lottoNumber -> lottoTicketToCompare.getLottoTicket()
                        .contains(lottoNumber))
                .count();
    }

    public Set<LottoNumber> getLottoTicket() {
        return lottoTicket;
    }
}
