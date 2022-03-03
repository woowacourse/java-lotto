package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import util.Validator;

public class Lotto {


    private final Set<LottoNumber> lottoNumbers;

    public Lotto(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = generateNumber(lottoNumberGenerator);
    }

    public static Lotto generateLotto(LottoNumberGenerator lottoNumberGenerator) {
        return new Lotto(lottoNumberGenerator);

    }

    public Lotto(Set<LottoNumber> lottoNumbers) {
        Validator.checkArgumentIsNull(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(this.lottoNumbers);
    }


    public Set<LottoNumber> generateNumber(LottoNumberGenerator lottoNumberGenerator) {
        List<Integer> pickedLottoNumbers = lottoNumberGenerator.generateLottoNumbers();

        return sortAndConvertToLottoNumberSet(pickedLottoNumbers);
    }

    public int countDuplicatedNumber(Lotto winningLotto) {
        Validator.checkArgumentIsNull(winningLotto);
        List<Integer> numbers = getNumbers();
        return (int) winningLotto.lottoNumbers
                .stream()
                .map(LottoNumber::getNumber)
                .filter(numbers::contains)
                .count();
    }

    public boolean isBonusNumberContain(LottoNumber bonusNumber) {
        Validator.checkArgumentIsNull(bonusNumber);
        return getNumbers().contains(bonusNumber.getNumber());
    }

    private Set<LottoNumber> sortAndConvertToLottoNumberSet(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private List<Integer> getNumbers() {
        return this.lottoNumbers
                .stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
