package domain.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningLotto {
    private static final int MIN_LOTTO_VALUE = 1;
    private static final int MAX_LOTTO_VALUE = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<Integer> winningLotto;

    public WinningLotto(List<Integer> winningLotto) {
        List<Integer> copy = new ArrayList<>(winningLotto);
        validateWinningLotto(copy);
        this.winningLotto = copy;
    }

    private void validateWinningLotto(final List<Integer> winningLotto) {
        validateDuplicate(winningLotto);
        validateNumber(winningLotto);
        validateLottoNumbersSize(winningLotto);
    }

    private void validateDuplicate(final List<Integer> winningLotto) {
        boolean isUnique = winningLotto.stream()
                .allMatch(new HashSet<>()::add);
        if (!isUnique) {
            throw new IllegalArgumentException("중복된 번호는 허용하지 않습니다.");
        }
    }

    private void validateNumber(final List<Integer> winningLotto) {
        boolean isBetweenNumber = winningLotto.stream()
                .allMatch(this::isBetweenNumber);
        if (!isBetweenNumber) {
            throw new IllegalArgumentException("1~45 사이의 번호만 허용합니다.");
        }
    }

    private void validateLottoNumbersSize(final List<Integer> winningLotto) {
        if (winningLotto.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("6개의 로또 번호가 필요합니다.");
        }
    }

    private boolean isBetweenNumber(final int number) {
        return number >= MIN_LOTTO_VALUE && number <= MAX_LOTTO_VALUE;
    }

}
