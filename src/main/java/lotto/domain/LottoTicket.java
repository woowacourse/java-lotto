package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        this.numbers = lottoTicket;
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public Rank compareNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        int totalMatchNumber = (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
        return getRank(bonusNumber, totalMatchNumber);
    }

    private Rank getRank(LottoNumber bonusNumber, int total) {
        if (numbers.contains(bonusNumber)) {
            return Rank.getMatchResultWithBonusNumber(total);
        }
        return Rank.getMatchResult(total);
    }
}
