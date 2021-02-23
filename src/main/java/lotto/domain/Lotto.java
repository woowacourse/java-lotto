package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lotto {

    public static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto() {
    }

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateNumberSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    public List<LottoNumber> lotto() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean containNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int countOfMatchNumber(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::containNumber)
                .count();
    }

    private void validateNumberSize(List<LottoNumber> lottoNumbers) {
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
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
