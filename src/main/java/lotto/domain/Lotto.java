package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int getMatchCount(Lotto lotto) {
        return (int) numbers.stream()
                .filter( l -> lotto.isContainLottoNumber(l))
                .count();
    }

}
