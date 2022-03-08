package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import util.Validator;

public class Lotto {

    private final Set<LottoNumber> numbers;

    public Lotto(LottoGenerator lottoGenerator) {
        this.numbers = generateNumber(lottoGenerator);
    }

    public Lotto(Set<LottoNumber> lottoNumbers) {
        Validator.checkArgumentIsNull(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    public static Lotto generateLotto(LottoGenerator lottoGenerator) {
        return new Lotto(lottoGenerator);

    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(this.numbers);
    }

    public int countDuplicatedNumber(Set<LottoNumber> winningNumbers) {
        Validator.checkArgumentIsNull(winningNumbers);
        return (int) winningNumbers
                .stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoNumber targetNumber) {
        boolean isContain = false;
        for (LottoNumber number : numbers) {
            isContain |= number.equals(targetNumber);
        }
        return isContain;
    }

    private Set<LottoNumber> generateNumber(LottoGenerator lottoGenerator) {
        List<LottoNumber> lottoNumbers = lottoGenerator.generateLottoNumber();
        return new TreeSet<>(lottoNumbers);
    }
}
