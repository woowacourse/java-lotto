package domain;

import java.util.*;

public class LottoNumbers {
    protected static final int LENGTH = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
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
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
