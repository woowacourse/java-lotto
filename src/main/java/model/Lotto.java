package model;

import constants.ErrorType;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_NUMBER_START_INCLUSIVE = 1;
    public static final int LOTTO_NUMBER_END_INCLUSIVE = 45;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream().sorted().toList();
    }

    public static Lotto from(final List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private void validateSize(final List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorType.LOTTO_NUMBER_IS_INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplicate(final List<LottoNumber> lottoNumbers) {
        final Set<LottoNumber> deduplicatedLottoNumber = new HashSet<>(lottoNumbers);
        if (deduplicatedLottoNumber.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(ErrorType.LOTTO_NUMBER_DUPLICATE.getMessage());
        }
    }

    public int calculateWinningNumbersMatchCount(final WinningLotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto::containsLottoNumber)
                .count();
    }

    public boolean isContainsNumber(final LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }
}
