package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;

import java.util.Set;
import java.util.TreeSet;

public class AutoLotto extends Lotto {
    public AutoLotto() {
        super(createRandomNumbers());
    }

    public static Set<LottoNumber> createRandomNumbers() {
        Set<LottoNumber> numbers = new TreeSet<>();
        while (numbers.size() < MAX_LOTTO_SIZE) {
            numbers.add(new LottoNumber());
        }
        return numbers;
    }
}
