package model;

import exception.DuplicatedLottoNumbersException;
import exception.InvalidLottoNumbersSizeException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    static final int LOTTO_NUMBER_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> lottoNumbers) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    private void checkLottoNumbers(List<Integer> lottoNumbers) {
        if (hasDuplicatedNumber(lottoNumbers)) {
            throw new DuplicatedLottoNumbersException();
        }
        if (isInvalidSize(lottoNumbers)) {
            throw new InvalidLottoNumbersSizeException();
        }
    }

    private boolean isInvalidSize(List<Integer> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_NUMBER_SIZE;
    }

    private boolean hasDuplicatedNumber(List<Integer> numbers) {
        return getDistinctSize(numbers) != numbers.size();
    }

    private long getDistinctSize(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(number));
    }

    public int getMatchedNumberCountWith(LottoNumbers otherLottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(number -> otherLottoNumbers.contains(number))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    public List<Integer> getIntValues() {
        return lottoNumbers.stream().map(LottoNumber::getIntValue).collect(Collectors.toList());
    }
}
