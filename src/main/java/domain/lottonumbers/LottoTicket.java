package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int SIZE = 6;

    protected final SortedSet<LottoNumber> lottoNumbers;

    public LottoTicket(Set<LottoNumber> numbers) {
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

    public int findDuplicatedNumbers(LottoTicket comparingTicket) {
        return (int) this.lottoNumbers.stream()
                .filter(comparingTicket::contains)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}