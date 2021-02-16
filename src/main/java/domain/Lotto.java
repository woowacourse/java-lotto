package domain;

import java.util.*;

public class Lotto {
    protected static final int LENGTH = 6;
    protected static final int NUMBER_MIN = 1;
    protected static final int NUMBER_MAX = 45;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
