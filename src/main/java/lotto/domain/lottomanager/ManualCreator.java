package lotto.domain.lottomanager;

import java.util.List;
import java.util.stream.Collectors;

public class ManualCreator implements LottoCreator {
    private static final String ERROR_NULL_LOTTO_NUMBERS = "ManualTickets() has Null";

    private List<LottoNumber> lottoNumbers;

    public ManualCreator(List<Integer> lottoNumbers) {
        lottoNumbers.forEach(ManualCreator::checkNullNumber);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public LottoTicket createTickets() {
        return new LottoTicket(lottoNumbers);
    }

    private static void checkNullNumber(Integer number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL_LOTTO_NUMBERS);
        }
    }
}
