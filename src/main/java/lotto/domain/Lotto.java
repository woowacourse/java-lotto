package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.exception.DuplicateLottoNumberException;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    protected final List<LottoNumber> lottoNumbers;

    public Lotto(final List<Integer> numbers) {
        validateDuplicatedNumber(numbers);
        lottoNumbers = numberToLottoNumbers(numbers);
    }

    private List<LottoNumber> numberToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    private void validateDuplicatedNumber(final List<Integer> values) {
        if (new HashSet<>(values).size() != LOTTO_SIZE) {
            throw new DuplicateLottoNumberException();
        }
    }

    public boolean isContainsNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
            .mapToInt(LottoNumber::getLottoNumber)
            .boxed()
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }


}
