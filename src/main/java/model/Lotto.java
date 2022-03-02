package model;

import exception.DuplicatedLottoNumbersException;
import exception.InvalidLottoNumbersSizeException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    private Lotto(Set<LottoNumber> lottoNumbers) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto of(List<LottoNumber> numbers) {
        if (isDuplicated(numbers)) {
            throw new DuplicatedLottoNumbersException();
        }
        List<LottoNumber> lottoNumbers = Collections.unmodifiableList(numbers);
        return new Lotto(Set.copyOf(lottoNumbers));
    }

    private static boolean isDuplicated(List<LottoNumber> numbers) {
        return numbers.size() != Set.copyOf(numbers).size();
    }

    private void checkLottoNumbers(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new InvalidLottoNumbersSizeException();
        }
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int getMatchedNumberCountWith(Lotto otherLotto) {
        return (int) this.lottoNumbers.stream()
                .filter(number -> otherLotto.contains(number))
                .count();
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto that = (Lotto) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
