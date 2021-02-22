package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.exception.LottoNumberException;

public class Lotto {

    private static final String JOIN_DELIMITER = ", ";
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new LottoNumberException("총 6 개의 숫자가 필요합니다.");
        }
        if (isDuplicated(lottoNumbers)) {
            throw new LottoNumberException("중복된 숫자가 입력이 되었습니다.");
        }
    }

    private boolean isDuplicated(final List<LottoNumber> lottoNumbers) {
        long count = lottoNumbers.stream()
                .mapToInt(LottoNumber::get)
                .distinct()
                .count();
        return count != lottoNumbers.size();
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int matchCount(final Lotto lotto) {
        return (int) lottoNumbers
                .stream()
                .filter(lotto::contains)
                .count();
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }
}
