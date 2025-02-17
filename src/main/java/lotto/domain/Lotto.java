package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.common.ErrorMessage.INVALID_LOTTO_NUM_SIZE;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validLottoNumbers(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
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

    public List<Integer> toIntegerList() {
        return sortedInAscending(lottoNumbers).stream()
                .map(LottoNumber::toInteger)
                .toList();
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}
