package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int VALID_LOTTO_NUMBER_COUNTS = 6;
    private static final String INVALID_LOTTO_NUMBER_COUNTS = "로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.";

    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        validateNumberCounts(lottoNumbers.size());
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket from(List<Integer> numbers) {
        validateNumberCounts(numbers.size());
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        return new LottoTicket(lottoNumbers);
    }

    private static void validateNumberCounts(int numberCounts) {
        if (numberCounts != VALID_LOTTO_NUMBER_COUNTS) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNTS);
        }
    }

    public int checkMatchCounts(LottoTicket lottoTicket) {
        return (int) lottoNumbers.stream()
                .filter(lottoTicket::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
