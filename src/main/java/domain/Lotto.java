package domain;

import java.util.Collections;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS = "%d개의 숫자를 골라주세요.";
    private static final String ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBERS = "숫자는 중복될 수 없습니다.";

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);

        this.lottoNumbers = Collections.unmodifiableSet(lottoNumbers);
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    String.format(ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS, LOTTO_SIZE));
        }
    }

    private void validateDuplicate(Set<LottoNumber> lottoNumbers) {
        if (isDuplicate(lottoNumbers)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private boolean isDuplicate(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != lottoNumbers.stream()
                .distinct()
                .count();
    }

    public int getSameNumberCount(Lotto anotherLotto) {
        return (int) lottoNumbers.stream()
                .filter(anotherLotto::containsLottoNumber)
                .count();
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
