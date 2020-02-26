package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class LottoNumbers {

    public static final int SIZE = 6;

    private final SortedSet<LottoNumber> lottoNumbers;

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

    public int findNumberOfMatchingNumbers(LottoNumbers comparingNumber) {
        return (int) this.lottoNumbers.stream()
                .filter(comparingNumber::contains)
                .count();
    }

    public Set<LottoNumber> getValue() {
        return this.lottoNumbers;
    }
}
