package lotto.domain;

import java.util.List;

public class LottoTicket {

    private final List<Integer> numbers;

    public LottoTicket(List<Integer> lottoTicket) {
        this.numbers = lottoTicket;
    }

    public Rank compareNumbers(List<Integer> winningNumbers, int bonusNumber) {
        int total = 0;
        for (Integer number : numbers) {
            if (winningNumbers.contains(number)) {
                total++;
            }
        }
        return getRank(bonusNumber, total);
    }

    private Rank getRank(int bonusNumber, int total) {
        if (total == 5 && numbers.contains(bonusNumber)) {
            return Rank.MATCH_FIVE_AND_BONUS_NUMBERS;
        }
        return Rank.matchResult(total);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
