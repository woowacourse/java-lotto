package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import util.Validator;

public class Lotto {

    private final Set<LottoNumber> numbers;

    public Lotto(LottoNumberGenerator lottoNumberGenerator) {
        this.numbers = generateNumber(lottoNumberGenerator);
    }

    public Lotto(Set<LottoNumber> lottoNumbers) {
        Validator.checkArgumentIsNull(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    public static Lotto generateLotto(LottoNumberGenerator lottoNumberGenerator) {
        return new Lotto(lottoNumberGenerator);

    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(this.numbers);
    }

    public int countDuplicatedNumber(Lotto winningLotto) {
        Validator.checkArgumentIsNull(winningLotto);
        return (int) winningLotto.numbers
                .stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        Validator.checkArgumentIsNull(number);
        return unboxNumbers().contains(number.getNumber());
    }

    private Set<LottoNumber> generateNumber(LottoNumberGenerator lottoNumberGenerator) {
        List<Integer> pickedLottoNumbers = lottoNumberGenerator.generateLottoNumbers();

        return sortAndConvertToLottoNumberSet(pickedLottoNumbers);
    }

    private Set<LottoNumber> sortAndConvertToLottoNumberSet(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private Set<Integer> unboxNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toSet());
    }
}
