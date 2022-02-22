package domain;

import java.util.List;

public class LottoTicket {

    private final LottoNumbers lottoNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        lottoNumbers = new LottoNumbers(numbers);
    }

    public LottoRank rank(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        int count = lottoNumbers.compareNumbers(winningNumbers);
        boolean hasBonus = lottoNumbers.contains(bonusNumber);

        return LottoRank.valueOf(count, hasBonus);
    }
}
