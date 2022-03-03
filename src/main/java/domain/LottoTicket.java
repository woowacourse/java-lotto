package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    private final LottoNumbers lottoNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        lottoNumbers = new LottoNumbers(numbers);
    }

    public LottoRank rank(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        int count = lottoNumbers.countDuplicateNumbers(winningNumbers);
        boolean hasBonus = lottoNumbers.contains(bonusNumber);

        return LottoRank.valueOf(count, hasBonus);
    }

    public List<Integer> getTicketNumbers() {
        return lottoNumbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getNumber)
                .boxed()
                .collect(Collectors.toList());
    }
}
