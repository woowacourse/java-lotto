package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final String ERROR_NUMBER_SIX_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String ERROR_DUPLICATION_MESSAGE = "로또 숫자는 중복되면 안됩니다.";
    private static final String ERROR_NULL_MESSAGE = "입력된 값이 null이면 안됩니다.";

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto(List<LottoNumber> numbers) {
        validateNumberSix(numbers);
        validateDuplication(numbers);

        this.numbers.addAll(numbers);
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

    public LottoPrize confirmWinning(WinningNumbers winningNumbers) {
        int lottoNumberMatches = (int) numbers.stream()
                .filter(winningNumbers::containsLottoNumber)
                .count();
        int bonusNumberMatches = (int) numbers.stream()
                .filter(winningNumbers::equalsBonusNumber)
                .count();

        return LottoPrize.match(lottoNumberMatches, bonusNumberMatches);
    }

    public boolean contains(LottoNumber target) {
        if (target == null) {
            throw new NullPointerException(ERROR_NULL_MESSAGE);
        }
        return numbers.contains(target);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
