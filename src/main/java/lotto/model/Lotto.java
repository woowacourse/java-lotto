package lotto.model;

import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

/*
 * 로또 한 장을 의미하는 Class
 */
public class Lotto {
    private final LottoNumbers numbers;
    private final boolean auto;

    public Lotto() {
        this.numbers = LottoNumbers.ofRandomNumbers();
        this.auto = true;
    }

    public Lotto(List<String> inputs) {
        this.numbers = LottoNumbers.from(inputs);
        this.auto = false;
    }

    public boolean contains(LottoNumber number) {
        return this.numbers.contains(number);
    }

    public int match(LottoNumbers comparingNumbers) {
        return this.numbers.match(comparingNumbers);
    }

    public LottoNumbers getNumbers() {
        return this.numbers;
    }

    public boolean isAuto() {
        return auto;
    }
}
