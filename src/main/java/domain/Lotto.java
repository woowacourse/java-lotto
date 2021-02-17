package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    protected static final int LENGTH = 6;
    private static final int PRICE = 1000;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = new ArrayList(lottoNumbers);
    }

    public Lotto(int[] lottoNumbers) {
        this(Arrays.stream(lottoNumbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    public static int calculateLottoNumber(Money money) {
        return money.divide(PRICE);
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        validateDuplicatedLottoNumbers(lottoNumbers);
        validateLottoNumbersLength(lottoNumbers);
    }

    private void validateDuplicatedLottoNumbers(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> distinctLottoNumbers = new HashSet(lottoNumbers);
        if (distinctLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateLottoNumbersLength(List<LottoNumber> lottoNumbers) {
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
