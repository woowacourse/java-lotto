package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumbers {

    public static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        lottoNumbers = new ArrayList<>();
    }

    public LottoNumbers(LottoNumbers lottoNumbers) {
        this(lottoNumbers.lottoNumbers());
    }

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> lottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public void sort() {
        Collections.sort(lottoNumbers);
    }

    public boolean containNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int countOfMatchNumber(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::containNumber)
                .count();
    }

    public void checkBonusNumber(LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("보너스 번호와 로또번호가 중복입니다.");
        }
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumberSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
    }

    private void validateLottoNumberSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또의 번호 개수가 맞지 않습니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        int numberAfterDistinct = (int) lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .distinct()
                .count();
        if (numberAfterDistinct != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
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

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
