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

    public double getMatchingCount(List<LottoNumber> numbers, LottoNumber bonusBall) {
        int matchingCount = getMatchingNumbersCount(numbers);
        if (matchingCount == 5 && isMatchingBonusNumber(bonusBall)){
            return matchingCount + 0.5;
        }
        return matchingCount;
    }

    private int getMatchingNumbersCount(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    private boolean isMatchingBonusNumber(LottoNumber bonusBall) {
        return lottoTicket.contains(bonusBall);
    }
}
