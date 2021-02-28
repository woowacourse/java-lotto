package lotto.domain;

import java.util.*;

public class LottoNumbers {

    public static final int LOTTO_NUMBER_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        this(changeToSet(lottoNumbers));
    }

    public LottoNumbers(LottoNumbers lottoNumbers) {
        this(lottoNumbers.lottoNumbers());
    }

    public LottoNumbers(Set<LottoNumber> lottoNumbers) {
        validateLottoNumberSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Set<LottoNumber> changeToSet(List<LottoNumber> list) {
        return new HashSet<>(list);
    }

    public Set<LottoNumber> lottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public boolean containNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int countOfMatchNumber(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::containNumber)
                .count();
    }

    private void validateLottoNumberSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또의 번호 개수가 맞지 않습니다.(중복된 번호를 쓰지 않았는지 확인해보세요)");
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
