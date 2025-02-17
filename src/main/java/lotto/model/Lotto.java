package lotto.model;

import static lotto.LottoNumberConstants.LOTTO_NUMBER_COUNT;

import java.util.HashSet;
import java.util.Set;

public class Lotto {

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        validateNumbersCount(numbers);
        this.numbers = numbers;
    }

    private void validateNumbersCount(Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d개여야 합니다.", LOTTO_NUMBER_COUNT.value()));
        }
    }

    public int getMatchCount(Lotto lotto) {
        Set<LottoNumber> me = new HashSet<>(Set.copyOf(this.numbers));
        me.retainAll(lotto.getLottoNumbers());
        return me.size();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Set.copyOf(numbers);
    }
}
