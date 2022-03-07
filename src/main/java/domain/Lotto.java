package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private static final String LOTTO_NUMBER_NULL_ERROR = "[ERROR] 로또 번호로 null 값이 올 수 없습니다.";
    private static final String LOTTO_SIZE_ERROR = "[ERROR] 로또 번호는 6개만 입력 가능합니다.";
    private static final String LOTTO_NUMBER_NOT_UNIQUE_ERROR = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        checkValidation(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    public int countWinningNumbers(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.stream()
            .filter(winningNumbers::isContain)
            .count();
    }

    public int countBonusNumber(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.stream()
            .filter(winningNumbers::isEqualToBonusNumber)
            .count();
    }

    private void checkValidation(List<LottoNumber> numbers) {
        checkNull(numbers);
        checkSize(numbers);
        checkUnique(numbers);
    }

    private void checkNull(List<LottoNumber> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NULL_ERROR);
        }
    }

    private void checkSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR);
        }
    }

    private void checkUnique(List<LottoNumber> numbers) {
        Set<LottoNumber> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NOT_UNIQUE_ERROR);
        }
    }
}
