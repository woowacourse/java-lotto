package lotto.domain.vo;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lotto.domain.LottoPrize;

public class Lotto {

    private static final String ERROR_NULL_MESSAGE = "로또 숫자가 없습니다.";
    private static final String ERROR_NUMBER_SIX_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String ERROR_DUPLICATION_MESSAGE = "로또 숫자는 중복되면 안됩니다.";

    public static final Money LOTTO_PRICE = new Money(1000);
    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateNull(numbers);
        validateNumberSix(numbers);
        validateDuplication(numbers);

        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateNull(List<LottoNumber> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    private void validateNumberSix(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NUMBER_SIX_MESSAGE);
        }
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        Set<LottoNumber> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_MESSAGE);
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public LottoPrize confirmWinning(WinningNumbers winningNumbers) {
        int lottoNumberMatchCount = (int) numbers.stream()
                .filter(winningNumbers::containsLottoNumber)
                .count();
        boolean bonusNumberMatch = numbers.stream()
                .anyMatch(winningNumbers::equalsBonusNumber);

        return LottoPrize.match(lottoNumberMatchCount, bonusNumberMatch);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
