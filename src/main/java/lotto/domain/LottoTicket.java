package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    public static final int LOTTO_LENGTH = 6;
    public static final String LOTTO_NUMBER_ERROR_MESSAGE = "로또의 숫자가 중복되었거나, 6개가 아닙니다.";
    private Set<LottoNumber> LottoTicket;

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        this.LottoTicket = lottoNumbers;
    }

    private void validateLottoLength(Set<LottoNumber> lottoNumbers) {
        if(lottoNumbers.size() != LOTTO_LENGTH){
            throw new IllegalArgumentException(LOTTO_NUMBER_ERROR_MESSAGE);
        }
    }


}
