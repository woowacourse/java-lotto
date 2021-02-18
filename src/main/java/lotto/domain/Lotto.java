package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBER_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateNumberSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

//    public Lotto(List<Integer> numbers) {
//        this(numbers.stream()
//                .map(LottoNumber::new)
//                .collect(Collectors.toList()));
//    }

    public List<LottoNumber> toList() {
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
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoNumbers);

        if (lottoNumberSet.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
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
