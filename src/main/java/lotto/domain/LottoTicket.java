package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTicket {

    private static final int DEFAULT_LOTTO_NUMBERS_SIZE = 6;
    private static final String DUPLICATED_LOTTO_NUMBERS_ERROR_MESSAGE = "로또는 6자리이며, 번호는 중복될 수 없습니다.";

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(LottoNumberGenerator lottoNumberGenerator) {
        this(lottoNumberGenerator.generate());
    }

    public LottoTicket(List<Integer> numbers) {
        Set<LottoNumber> lottoNumber = numbers.stream()
                .map(LottoNumber::new)
                .collect(toSet());

        validateSize(lottoNumber);
        this.lottoNumbers = lottoNumber;
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != DEFAULT_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBERS_ERROR_MESSAGE);
        }
    }

    public boolean isSame(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
