package lotto.model.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.lottoNumbers = toLottoNumbers(numbers);
    }

    private List<LottoNumber> toLottoNumbers(final List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::draw)
                .toList();
    }

    private void validate(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자가 %d개가 아닙니다.".formatted(LOTTO_SIZE));
        }
    }

    private void validateDuplication(final List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
    }

    public boolean hasNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch((thisLottoNumber) -> thisLottoNumber.equals(lottoNumber));
    }

    public int calculateMatchingCount(final Lotto otherLotto) {
        return (int) otherLotto.lottoNumbers.stream()
                .filter(this::hasNumber)
                .count();
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .toList();
    }

}
