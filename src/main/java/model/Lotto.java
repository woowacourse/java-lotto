package model;

import constans.ErrorType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    private void validateSize(final List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorType.LOTTO_NUMBER_IS_INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplicate(final List<LottoNumber> lottoNumbers) {
        final Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != 6) {
            throw new IllegalArgumentException(ErrorType.LOTTO_NUMBER_DUPLICATE.getMessage());
        }
    }
}
