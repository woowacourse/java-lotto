package lotto.domain;

import lotto.utils.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> numbers) {
        Validator.validateLottoNumbers(numbers);
        this.lottoTicket = new ArrayList<>(numbers);
    }

    public List<LottoNumber> lottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public int getMatchingCount(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    public boolean isMatchingBonusNumber(LottoNumber bonusBall) {
        return lottoTicket.contains(bonusBall);
    }

    public boolean isContainLottoNumber(LottoNumber lottoNumber){
        return lottoTicket.contains(lottoNumber);
    }
}
