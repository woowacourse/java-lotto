package lotto.model.lottofactory;

import static lotto.ValidationUtils.*;

import java.util.Set;
import java.util.TreeSet;

import lotto.model.LottoNumber;
import lotto.model.Rank;

public class Lotto {
    static final int LOTTO_SIZE = 6;
    private static final String ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE = "로또 번호 개수는 6개로 입력해주세요.";

    private final Set<LottoNumber> lottoNumbers;

    Lotto(Set<LottoNumber> inputLottoNumbers) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>(inputLottoNumbers);
        validateEmptyCollection(lottoNumbers);
        validateNumberOfLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateNumberOfLottoNumbers(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE);
        }
    }

    public Rank match(Lotto winningNumbers, LottoNumber bonusNumber) {
        return Rank.find(getMatchScore(winningNumbers), isMatchNumber(bonusNumber));
    }

    private int getMatchScore(Lotto winningNumbers) {
        return (int)lottoNumbers.stream()
            .filter(winningNumbers::isMatchNumber)
            .count();
    }

    private boolean isMatchNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
