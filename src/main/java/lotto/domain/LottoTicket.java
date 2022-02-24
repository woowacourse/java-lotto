package lotto.domain;

import java.util.List;

public class LottoTicket {

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        this.numbers = lottoTicket;
    }

    public Rank compareNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        int total = 0;
        for (LottoNumber number : numbers) {
            if (winningNumbers.contains(number)) {
                total++;
            }
        }
        return getRank(bonusNumber, total);
    }

    private Rank getRank(LottoNumber bonusNumber, int total) {
        if (total == 5 && numbers.contains(bonusNumber)) {
            return Rank.MATCH_FIVE_AND_BONUS_NUMBERS;
        }
        return Rank.matchResult(total);
    }
    
    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
