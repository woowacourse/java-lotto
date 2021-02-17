package domain;

import java.util.*;

public class Lotto {
    protected static final int LENGTH = 6;
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
