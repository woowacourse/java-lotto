package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
<<<<<<< HEAD
=======
import java.util.stream.IntStream;
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
import util.Validator;

public class Lotto {

<<<<<<< HEAD
    private final Set<LottoNumber> lottoNumbers;

    public Lotto(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = generateNumber(lottoNumberGenerator);
    }

    public static Lotto generateLotto(LottoNumberGenerator lottoNumberGenerator) {
        return new Lotto(lottoNumberGenerator);
=======
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateNumber();
    }

    public static Lotto generateLotto() {
        return new Lotto();
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
    }

    public Lotto(Set<LottoNumber> lottoNumbers) {
        Validator.checkArgumentIsNull(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(this.lottoNumbers);
    }

<<<<<<< HEAD
    public Set<LottoNumber> generateNumber(LottoNumberGenerator lottoNumberGenerator) {
        List<Integer> pickedLottoNumbers = lottoNumberGenerator.generateLottoNumbers();
=======
    public Set<LottoNumber> generateNumber() {
        List<Integer> lottoNumberCandidates = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        List<Integer> pickedLottoNumbers = pickLottoNumbersFromCandidates(lottoNumberCandidates);
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
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

<<<<<<< HEAD
=======
    private List<Integer> pickLottoNumbersFromCandidates(List<Integer> cadidates) {
        Collections.shuffle(cadidates);
        return cadidates.subList(MIN_RANGE, MAX_RANGE);
    }

>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
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
