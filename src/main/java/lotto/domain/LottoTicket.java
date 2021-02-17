package lotto.domain;

import lotto.utils.Validator;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private final List<Integer> lottoTicket;

    public LottoTicket(List<Integer> numbers) {
        Validator.validateLottoNumbers(numbers);
        Collections.shuffle(numbers);
        this.lottoTicket = numbers.subList(0, 6);
    }

    public List<Integer> lottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public double getMatchingCount(List<Integer> numbers, int bonusBall) {
        int matchingCount = getMatchingNumbersCount(numbers);
        if (matchingCount == 5 && isMatchingBonusNumber(bonusBall)){
            return matchingCount + 0.5;
        }
        return matchingCount;
    }

    private int getMatchingNumbersCount(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    private boolean isMatchingBonusNumber(int bonusBall) {
        return lottoTicket.contains(bonusBall);
    }
}
