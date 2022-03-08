package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    private Lotto(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호 갯수는 6개여야 합니다.");
        }
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    public static Lotto of(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException("중복된 로또 번호는 입력할 수 없습니다.");
        }
        Set<LottoNumber> lottoNumbers = convertAll(numbers);
        return new Lotto(lottoNumbers);
    }

    private static boolean isDuplicated(List<Integer> numbers) {
        return numbers.size() != Set.copyOf(numbers).size();
    }

    private static Set<LottoNumber> convertAll(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toSet());
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int getMatchedNumberCountWith(Lotto otherLotto) {
        return (int) this.lottoNumbers.stream()
                .filter(otherLotto::contains)
                .count();
    }

    public Set<LottoNumber> getLottoNumbers() {
        return new HashSet<>(lottoNumbers);
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
