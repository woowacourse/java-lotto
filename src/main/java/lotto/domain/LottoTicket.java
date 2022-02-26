package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        this.numbers = lottoTicket;
    }

    public Rank compareNumbers(Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        int totalMatchNumber = (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
        return getRank(bonusNumber, totalMatchNumber);
    }

    private Rank getRank(LottoNumber bonusNumber, int total) {
        if (total == Rank.THIRD.getMatchCount() && numbers.contains(bonusNumber)) {
            return Rank.SECOND;
        }
        return Rank.matchResult(total);
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
