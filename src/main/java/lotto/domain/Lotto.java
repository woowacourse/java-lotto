package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.common.ErrorMessage.INVALID_LOTTO_NUM_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

public class Lotto {

    private static final String DELIMITER = ",";
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public long getMatchCount(Lotto winnerNumbers) {
        return lottoNumbers.stream()
                .filter(winnerNumbers.lottoNumbers::contains)
                .count();
    }

    public boolean hasLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private void validLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUM_SIZE.getMessage());
        }

        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalStateException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    private List<LottoNumber> sortedInAscending(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(DELIMITER + " ", "[", "]");

        for (LottoNumber lottoNumber : sortedInAscending(lottoNumbers)) {
            joiner.add(lottoNumber.toString());
        }

        return joiner.toString();
    }
}
