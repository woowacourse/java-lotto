package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    private static final int DEFAULT_LOTTO_NUMBERS_SIZE = 6;
    private static final String DUPLICATED_LOTTO_NUMBERS_ERROR_MESSAGE = "번호의 갯수가 적절하지 않습니다. 또한 중복될 수 없습니다.";

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        Set<LottoNumber> lottoNumber = numbers.stream()
                .collect(toSet());

        validateSize(lottoNumber);
        this.lottoNumbers = lottoNumber;
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != DEFAULT_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBERS_ERROR_MESSAGE);
        }
    }

    public int getSameNumberCount(LottoTicket lottoTicket) {
        return (int) lottoTicket.lottoNumbers
                .stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
