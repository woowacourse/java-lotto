package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoNumbers {

    public static final int SIZE = 6;

    protected final SortedSet<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<LottoNumber> numbers) {
        validateSize(numbers);
        this.lottoNumbers = new TreeSet<>(numbers);
    }

    private void validateSize(Set<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("6개의 중복되지 않는 숫자를 입력해주세요.");
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
