package lotto.domain;

import lotto.exception.LottoCustomException;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(final Set<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(final List<Integer> numbers) {
        final Set<LottoNumber> lottoNumbers = convertToSet(numbers);
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private Set<LottoNumber> convertToSet(List<Integer>numbers) {
        return numbers.stream().map(LottoNumber::from).collect(Collectors.toSet());
    }

    private void validate(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new LottoCustomException(String.format("로또는 총 %d개의 번호로 이루어져야합니다.", LOTTO_SIZE));
        }
    }

    public Set<Integer> toSet() {
        Set<Integer> numbers = new HashSet<>();
        lottoNumbers.forEach(lottoNumber -> numbers.add(lottoNumber.getNumber()));
        return numbers;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
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
        return lottoNumbers.equals(lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
