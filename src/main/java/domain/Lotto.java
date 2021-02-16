package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LENGTH = 6;
    private static final int NUMBER_MIN = 1;
    private static final int NUMBER_MAX = 45;
    private static final int PRICE = 1000;

    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = new ArrayList(lottoNumbers);
    }

    public static int calculateLottoNumber(Money money) {
        return money.divide(PRICE);
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        validateDuplicatedLottoNumbers(lottoNumbers);
        validateLottoNumbersLength(lottoNumbers);
        validateLottoNumbersInRange(lottoNumbers);
    }

    private void validateDuplicatedLottoNumbers(List<Integer> lottoNumbers) {
        Set<Integer> distinctLottoNumbers = new HashSet(lottoNumbers);
        if (distinctLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateLottoNumbersLength(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6자리입니다.");
        }
    }

    private void validateLottoNumbersInRange(List<Integer> lottoNumbers) {
        for (int lottoNumber : lottoNumbers) {
            validateLottoNumberInRange(lottoNumber);
        }
    }

    private void validateLottoNumberInRange(int lottoNumber) {
        if (lottoNumber < NUMBER_MIN || NUMBER_MAX < lottoNumber) {
            throw new IllegalArgumentException("로또 숫자 범위 외 숫자입니다.");
        }
    }
}
