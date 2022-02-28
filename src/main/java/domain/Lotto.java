package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

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
    }

    public Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public Set<LottoNumber> generateNumber() {
        List<Integer> lottoNumberCandidates = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        List<Integer> pickedLottoNumbers = pickLottoNumbersFromCandidates(lottoNumberCandidates);
        return sortAndConvertToLottoNumberSet(pickedLottoNumbers);
    }

    public int countDuplicatedNumber(Lotto winningLotto) {
        List<Integer> numbers = getNumbers();
        return (int) winningLotto.lottoNumbers
                .stream()
                .map(LottoNumber::getNumber)
                .filter(numbers::contains)
                .count();
    }

    public boolean isBonusNumberContain(LottoNumber bonusNumber) {
        return getNumbers().contains(bonusNumber.getNumber());
    }

    private List<Integer> pickLottoNumbersFromCandidates(List<Integer> cadidates) {
        Collections.shuffle(cadidates);
        return cadidates.subList(MIN_RANGE, MAX_RANGE);
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
