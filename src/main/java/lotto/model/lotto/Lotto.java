package lotto.model.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(final List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = toSortedLottoNumbers(lottoNumbers);
    }

    private TreeSet<LottoNumber> toSortedLottoNumbers(final List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::draw)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private void validate(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
        }
    }

    private void validateDuplication(final List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
    }

    public boolean has(final LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch((thisLottoNumber) -> thisLottoNumber.equals(lottoNumber));
    }

    public int calculateMatchingCount(final Lotto otherLotto) {
        return (int) otherLotto.lottoNumbers.stream()
                .filter(this::has)
                .count();
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .toList();
    }

}
