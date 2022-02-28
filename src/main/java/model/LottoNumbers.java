package model;

import exception.DuplicatedLottoNumbersException;
import exception.InvalidLottoNumbersSizeException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    private LottoNumbers(Set<LottoNumber> lottoNumbers) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumbers of(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new DuplicatedLottoNumbersException();
        }
        return new LottoNumbers(
                numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet())
        );
    }

    private static <T extends Collection<Integer>> boolean isDuplicated(T numbers) {
        return numbers.size() != Set.copyOf(numbers).size();
    }

    private void checkLottoNumbers(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new InvalidLottoNumbersSizeException();
        }
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

    public List<Integer> getIntValues() {
        return lottoNumbers.stream().map(LottoNumber::getIntValue).collect(Collectors.toList());
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
}
