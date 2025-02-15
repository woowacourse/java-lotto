package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.common.ErrorMessage.INVALID_LOTTO_INPUT;
import static lotto.common.ErrorMessage.INVALID_LOTTO_NUM_SIZE;
import static lotto.common.ErrorMessage.USE_SEPARATOR;

import java.util.Arrays;
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

    public static Lotto from(List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lotto from(String input) {
        if (input == null || input.isBlank() || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(INVALID_LOTTO_INPUT.getMessage());
        }

        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException(USE_SEPARATOR.getMessage());
        }

        return new Lotto(Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(LottoNumber::from)
                .toList());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public long getMatchCount(Lotto winnerNumbers) {
        return winnerNumbers.getMatchCount(lottoNumbers);
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    private long getMatchCount(List<LottoNumber> winnerNumbers) {
        return lottoNumbers.stream()
                .filter(winnerNumbers::contains)
                .count();
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
