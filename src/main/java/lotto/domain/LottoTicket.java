package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int VALID_LOTTO_NUMBER_COUNTS = 6;
    private static final String INVALID_LOTTO_NUMBER_COUNTS = "로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.";
    
    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket generateTicket(List<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        validateNumbers(numbers.size(), lottoNumbers.size());
        return new LottoTicket(lottoNumbers);
    }

    private static void validateNumbers(int numberCounts, int distinctNumberCounts) {
        if (numberCounts != VALID_LOTTO_NUMBER_COUNTS || numberCounts != distinctNumberCounts) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNTS);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public int getMatchCounts(LottoTicket winningTicket) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoNumber -> winningTicket.contains(lottoNumber))
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }
}
