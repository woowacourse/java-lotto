package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    private final LottoTicketNumbers lottoTicketNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        lottoTicketNumbers = new LottoTicketNumbers(numbers);
    }

    public LottoRank rank(LottoTicketNumbers winningNumbers, LottoNumber bonusNumber) {
        int count = lottoTicketNumbers.countDuplicateNumbers(winningNumbers);
        boolean hasBonus = lottoTicketNumbers.contains(bonusNumber);

        return LottoRank.valueOf(count, hasBonus);
    }

    public List<Integer> getTicketNumbers() {
        return lottoTicketNumbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getNumber)
                .boxed()
                .collect(Collectors.toList());
    }
}
