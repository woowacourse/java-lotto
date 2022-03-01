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
        return Rank.getMatchResult(totalMatchNumber, numbers.contains(bonusNumber));
    }
}
