package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {

    private static final String ERROR_NUMBER_SIX_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String ERROR_DUPLICATION_MESSAGE = "로또 숫자는 중복되면 안됩니다.";
    private static final String ERROR_NULL_MESSAGE = "입력된 값이 null이면 안됩니다.";

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        Objects.requireNonNull(numbers, ERROR_NULL_MESSAGE);
        this.numbers = new ArrayList<>(numbers);

        validateNumberSix(this.numbers);
        validateDuplication(this.numbers);
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

    public LottoPrize confirmWinning(WinningLotto winningLotto) {
        int lottoNumberMatches = (int) numbers.stream()
                .filter(winningLotto::containsLottoNumber)
                .count();

        return LottoPrize.match(lottoNumberMatches, winningLotto.containsBonusNumber(this));
    }

    public boolean contains(LottoNumber target) {
        Objects.requireNonNull(target, ERROR_NULL_MESSAGE);

        return numbers.contains(target);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
