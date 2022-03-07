package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {

    private static final int LOTTO_NUMBERS_SIZE = 6;

    private static final String LOTTO_NUMBERS_NULL_ERROR = "[ERROR] 로또 번호에는 NULL 값이 올 수 없습니다.";
    private static final String LOTTO_NUMBERS_EMPTY_ERROR = "[ERROR] 로또 번호에는 빈 값이 올 수 없습니다.";
    private static final String LOTTO_NUMBERS_SIZE_ERROR = "[ERROR] 로또 번호는 6개만 입력 가능합니다.";
    private static final String LOTTO_NUMBERS_NOT_UNIQUE_ERROR = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        checkValidate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    private void checkValidate(List<LottoNumber> numbers) {
        checkNull(numbers);
        checkEmpty(numbers);
        checkSize(numbers);
        checkUnique(numbers);
    }

    private void checkNull(List<LottoNumber> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_NULL_ERROR);
        }
    }

    private void checkEmpty(List<LottoNumber> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_EMPTY_ERROR);
        }
    }


    private void checkSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE_ERROR);
        }
    }

    private void checkUnique(List<LottoNumber> numbers) {
        Set<LottoNumber> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_NOT_UNIQUE_ERROR);
        }
    }


}
