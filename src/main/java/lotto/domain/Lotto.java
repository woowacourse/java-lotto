package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {

        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }


    public boolean isContain(int bonusBall) {
        return numbers.contains(LottoNumber.getLottoNumber(bonusBall));
    }
}
